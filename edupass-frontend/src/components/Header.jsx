import { useState, useRef, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import logo from '../../public/assets/logos/default.png';

import { User, UserCheck, Briefcase, Users, LogOut } from "lucide-react";

const UserIcon = () => <User className="w-5 h-5 text-slate-600 group-hover:text-blue-600" />;
const ProfileIcon = () => <UserCheck className="w-5 h-5 mr-3 text-slate-500 group-hover:text-blue-600 transition-colors" />;
const BriefcaseIcon = () => <Briefcase className="w-5 h-5 mr-3 text-slate-500 group-hover:text-blue-600 transition-colors" />;
const UsersGroupIcon = () => <Users className="w-5 h-5 mr-3 text-slate-500 group-hover:text-blue-600 transition-colors" />;
const LogoutIcon = () => <LogOut className="w-5 h-5 mr-3 text-slate-500 group-hover:text-red-500 transition-colors" />;

export default function Header({ userLoggedIn, setUserLoggedIn }) {
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);
  const [isMobileMenuOpen, setIsMobileMenuOpen] = useState(false);
  const dropdownRef = useRef(null);
  const navigate = useNavigate();
  const user = userLoggedIn ? JSON.parse(localStorage.getItem("user")) : null;

  useEffect(() => {
    const handleClickOutside = (event) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target)) {
        setIsDropdownOpen(false);
      }
    };
    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, []);

  const handleLogout = () => {
    // Alerta de confirmação de UX antes de sair
    if (window.confirm("Você tem certeza que deseja sair da plataforma?")) {
      localStorage.removeItem("token");
      localStorage.removeItem("user");
      setUserLoggedIn(false);
      setIsDropdownOpen(false);
      setIsMobileMenuOpen(false);
      navigate("/login");
    }
  };

  const closeMobileMenu = () => setIsMobileMenuOpen(false);

  return (
    <header className="sticky top-0 z-50 bg-white shadow-sm border-b border-gray-100">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex items-center h-16 w-full">
          <nav className="hidden md:flex items-center space-x-10 flex-1">    
            
          <div className="flex-shrink-0">
            <Link to="/">
              <img src={logo} alt="Logo" className="w-32 cursor-pointer hover:opacity-90 transition-opacity" />
            </Link>
          </div>
            <Link to="/#comofunciona"
            className="text-sm font-medium text-gray-700 hover:text-blue-500 transition-colors">Como Funciona</Link>
            <Link
              to="/beneficios"
              className="text-sm font-medium text-gray-700 hover:text-blue-500 transition-colors"
            >
              Benefícios
            </Link>
            <Link
              to="/suport"
              className="text-sm font-medium text-gray-700 hover:text-blue-500 transition-colors"
            >
              Suporte
            </Link>
            {userLoggedIn && user?.roles?.includes("ROLE_ADMIN") && (
              <Link
                to="/admin/dashboard"
                className="text-sm font-medium text-blue-600 hover:text-blue-700 transition-colors"
              >
                Painel Admin
              </Link>
            )}

            {userLoggedIn ? (
              <div ref={dropdownRef} className="relative ml-auto">
                <button
                onClick={() => setIsDropdownOpen(!isDropdownOpen)}
                className="flex items-center gap-2 px-4 py-2 rounded-full bg-slate-100 hover:bg-slate-200 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2 transition-colors"
                aria-label="Menu do usuário"
                aria-expanded={isDropdownOpen}
                aria-haspopup="true"
              >
                <UserIcon />
                <span className="text-sm font-medium text-gray-700">Área do colaborador</span>
              </button>

                {isDropdownOpen && (
                  <div
                    className="absolute right-0 mt-2 w-64 bg-white rounded-lg shadow-xl ring-1 ring-black ring-opacity-5 py-1 origin-top-right transition-all duration-150 ease-out"
                    role="menu"
                  >
                    {user && (
                      <div className="px-4 py-3 border-b border-slate-100">
                        <p className="text-sm font-medium text-slate-800 truncate">{user.fullName || user.email || "Usuário"}</p>
                        {user.email && user.fullName && <p className="text-xs text-slate-500 truncate">{user.email}</p>}
                      </div>
                    )}
                    <div className="py-1">
                      <Link to="/perfil" onClick={() => setIsDropdownOpen(false)} className="group flex items-center px-4 py-2.5 text-sm text-slate-700 hover:bg-slate-100 hover:text-blue-600 w-full transition-colors ">
                        <ProfileIcon /> Perfil
                      </Link>
                      <Link to="/minhas-bolsas" onClick={() => setIsDropdownOpen(false)} className="group flex items-center px-4 py-2.5 text-sm text-slate-700 hover:bg-slate-100 hover:text-blue-600 w-full transition-colors">
                        <BriefcaseIcon /> Minhas Bolsas
                      </Link>
                    </div>
                    <div className="border-t border-slate-100 py-1">
                      <button onClick={handleLogout} className="group flex items-center px-4 py-2.5 text-sm text-red-600 hover:bg-red-50 hover:text-red-700 w-full transition-colors text-left">
                        <LogoutIcon /> Sair
                      </button>
                    </div>
                  </div>
                )}
              </div>
            ) : (
              <div className="flex items-center space-x-3 ml-auto">
                <Link
                  to="/cadastro"
                  className="btn btn-secondary text-sm px-4 py-2"
                >
                  Cadastre-se Grátis
                </Link>
                <Link
                  to="/login"
                  className="btn btn-primary text-sm px-6 py-2"
                >
                  Entrar
                </Link>
              </div>
            )}
          </nav>

          {/* Botão do Menu Hambúrguer (Mobile) */}
          <div className="md:hidden flex items-center w-full">
            <Link to="/">
              <img src={logo} alt="Logo" className="w-32 cursor-pointer" />
            </Link>
            <button
              onClick={() => setIsMobileMenuOpen(!isMobileMenuOpen)}
              type="button"
              className="inline-flex items-center justify-center p-2 rounded-md text-slate-500 hover:text-slate-700 hover:bg-slate-100 focus:outline-none focus:ring-2 focus:ring-inset focus:ring-blue-500 ml-auto"
              aria-controls="mobile-menu"
              aria-expanded={isMobileMenuOpen}
            >
              <span className="sr-only">Abrir menu principal</span>
              {isMobileMenuOpen ? (
                <svg className="block h-6 w-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M6 18L18 6M6 6l12 12" /></svg>
              ) : (
                <svg className="block h-6 w-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h16M4 18h16" /></svg>
              )}
            </button>
          </div>
        </div>
      </div>

      {/* Menu Mobile */}
      {isMobileMenuOpen && (
        <div className="md:hidden absolute top-16 inset-x-0 bg-white shadow-lg z-40 p-2 transition transform origin-top" id="mobile-menu">
          <div className="pt-2 pb-3 space-y-1">
            <Link
              to="/#comofunciona"
              onClick={closeMobileMenu}
              className="block px-4 py-2 text-base font-medium text-gray-700 hover:text-blue-500 hover:bg-blue-50 rounded transition-colors"
            >
              Como funciona
            </Link>
            <Link
              to="/beneficios"
              onClick={closeMobileMenu}
              className="block px-4 py-2 text-base font-medium text-gray-700 hover:text-blue-500 hover:bg-blue-50 rounded transition-colors"
            >
              Benefícios
            </Link>
            <Link
              to="/suport"
              onClick={closeMobileMenu}
              className="block px-4 py-2 text-base font-medium text-gray-700 hover:text-blue-500 hover:bg-blue-50 rounded transition-colors"
            >
              Suporte
            </Link>
            {userLoggedIn && user?.roles?.includes("ROLE_ADMIN") && (
              <Link to="/admin/dashboard" onClick={closeMobileMenu} className="block px-4 py-2 rounded-md text-base font-medium text-blue-600 hover:bg-blue-50">Painel Admin</Link>
            )}
          </div>
          {userLoggedIn ? (
            <div className="pt-4 pb-3 border-t border-slate-200">
              {user && (
                <div className="flex items-center px-4 mb-3">
                  <div className="flex-shrink-0 mr-3">
                    <div className="flex items-center justify-center w-10 h-10 rounded-full bg-slate-200 text-slate-600">
                      <UserIcon />
                    </div>
                  </div>
                  <div>
                    <div className="text-base font-medium text-slate-800">{user.fullName || user.email || "Usuário"}</div>
                    {user.email && user.fullName && <div className="text-sm font-medium text-slate-500">{user.email}</div>}
                  </div>
                </div>
              )}
              <div className="space-y-1">
                <Link to="/perfil" onClick={closeMobileMenu} className="group flex items-center px-4 py-2.5 text-base font-medium text-slate-700 hover:bg-slate-50 hover:text-blue-600 rounded-md"><ProfileIcon /> Perfil</Link>
                <Link to="/minhas-bolsas" onClick={closeMobileMenu} className="group flex items-center px-4 py-2.5 text-base font-medium text-slate-700 hover:bg-slate-50 hover:text-blue-600 rounded-md"><BriefcaseIcon /> Minhas Bolsas</Link>
                <button onClick={handleLogout} className="group flex items-center px-4 py-2.5 text-base font-medium text-red-600 hover:bg-red-50 hover:text-red-700 w-full text-left rounded-md"><LogoutIcon /> Sair</button>
              </div>
            </div>
          ) : (
            <div className="pt-4 pb-3 border-t border-slate-200 space-y-3 px-4 flex flex-col">
              <Link
                  to="/login"
                  onClick={closeMobileMenu}
                  className="btn btn-primary text-center w-full"
                >
                  Entrar
                </Link>
               <Link
                  to="/cadastro"
                  onClick={closeMobileMenu}
                  className="btn btn-secondary text-center w-full"
                >
                  Cadastre-se Grátis
                </Link>
            </div>
          )}
        </div>
      )}
    </header>
  );
}

