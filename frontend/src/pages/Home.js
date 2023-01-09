import React from 'react';

const Home = () => {
  const isLogado = window.localStorage.getItem('token');
  if (!isLogado) window.location.href = '/';
  else return <div>Bem vindo a home</div>;
};

export default Home;
