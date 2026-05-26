import React, { useEffect, useState, useRef } from 'react';

export function ModalDialog({ isOpen, onClose, children }) {
  const [show, setShow] = useState(false);
  const modalRef = useRef(null);

  useEffect(() => {
    if (isOpen) {
      setShow(true);
      // Foca no modal automaticamente por questões de acessibilidade
      setTimeout(() => modalRef.current?.focus(), 10);
    } else {
      const timeout = setTimeout(() => setShow(false), 300);
      return () => clearTimeout(timeout);
    }
  }, [isOpen]);

  // Fechar com a tecla Esc
  useEffect(() => {
    const handleKeyDown = (e) => {
      if (e.key === 'Escape' && isOpen) {
        onClose();
      }
    };
    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, [isOpen, onClose]);

  // Fechar ao clicar fora do conteúdo (Backdrop)
  const handleBackdropClick = (e) => {
    if (e.target === e.currentTarget) {
      onClose();
    }
  };

  if (!isOpen && !show) return null;

  return (
    <div 
      className="fixed inset-0 flex items-center justify-center z-50 backdrop-blur-sm bg-black/20"
      onClick={handleBackdropClick} // Clicar fora fecha
    >
      <div
        ref={modalRef}
        tabIndex="-1" // Permite receber foco via JS (Focus Trap inicial)
        role="dialog"
        aria-modal="true"
        aria-labelledby="modal-title"
        className={`relative bg-white rounded-lg shadow-xl p-6 w-full max-w-xl mx-4 transform transition-all duration-300 outline-none
          ${isOpen ? 'opacity-100 scale-100' : 'opacity-0 scale-95'}
        `}
      >
        <button
          onClick={onClose}
          className="absolute top-4 right-4 text-gray-400 hover:text-gray-700 hover:bg-gray-100 rounded-full p-1 transition-colors focus:outline-none focus:ring-2 focus:ring-blue-500"
          aria-label="Fechar janela"
        >
          ✕
        </button>
        <div id="modal-title" className="sr-only">Caixa de diálogo</div>
        {children}
      </div>
    </div>
  );
}

