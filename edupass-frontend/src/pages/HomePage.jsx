import React, { useState, useEffect } from 'react';
import { useLocation } from "react-router-dom";
import FiltroCursos from "../components/FiltroCursos";
import OpportunitiesList from "../components/OpportunitiesList";
import Steps from "../components/Steps";
import About from "../components/About";
import Footer from "../components/Footer";

function HomePage() {
  const [filters, setFilters] = useState({
    tab: 'Escola',
    cidade: '',
    curso: '',
    instituicao: '',
    bolsa: 80,
    // modalidade: { presencial: true, ead: true }
  });

  const location = useLocation();

  useEffect(() => {
    if (location.hash) {
      const id = location.hash.replace("#", "");
      const el = document.getElementById(id);
      if (el) {
        el.scrollIntoView({ behavior: "smooth" });
      }
    }
  }, [location]);

  const handleBuscar = (newFilters) => {
    setFilters(newFilters);
  };

  return (
    <div className="bg-white">
      {/* Hero Section */}
      <section className="relative w-full h-[340px] md:h-[670px] lg:h-[472px] bg-cover bg-center bg-[url('/assets/banners/banner-proledupass-mobile.jpeg')] lg:bg-[url('/src/assets/banner5.png')]">

  <div className="absolute inset-0 flex items-center justify-between px-8 lg:px-20">

    <div className="hidden lg:block text-white">
      <h2 className="text-3xl font-bold uppercase leading-tight">
        Bolsas de
        <br />
        Estudo
      </h2>

      <p className="mt-2 text-xl">
        com até
      </p>

      <p className="text-7xl font-bold text-yellow-400 leading-none">
        80%
      </p>

      <span className="inline-block mt-2 px-3 py-1 bg-white text-sky-500 rounded-full">
        de desconto
      </span>
    </div>

    <div className="hidden lg:block w-[400px] text-white text-right">
      <h2 className="text-4xl font-bold uppercase">
        Seu futuro começa com o{" "}
        <span className="text-yellow-400">
          PROL EDU PASS
        </span>
      </h2>

      <p className="mt-10 text-lg">
        O ProlEdu Pass conecta você a novas possibilidades.
      </p>

      <p className="mt-2 text-lg">
        Sua jornada educacional começa com quem acredita no seu futuro.
      </p>
    </div>

  </div>

</section>
      
      <FiltroCursos onBuscar={handleBuscar} initialFilters={filters} />
      <OpportunitiesList currentFilters={filters} />
      <Steps />
      <About />
      <Footer />
    </div>
  );
}

export default HomePage;
