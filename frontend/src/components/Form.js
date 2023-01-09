import React from 'react';

const Form = () => {
  const [email, setEmail] = React.useState('');
  const [senha, setSenha] = React.useState('');

  const [msg, setMsg] = React.useState('');

  const data = {
    email,
    senha,
  };

  async function testeApi(e) {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/v1/usuario/auth', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      });
      if (response.status === 200) {
        const dados = await response.json();
        console.log(dados);
        window.localStorage.setItem('token', dados.accessToken);
        window.location.href = '/home';
      } else {
        throw new Error();
      }
    } catch (erro) {
      console.log(erro);
      setMsg('Usuario ou senha inválidos');
    }
  }

  return (
    <>
      <form onSubmit={testeApi}>
        <input
          type="email"
          placeholder="Email"
          onChange={(e) => setEmail(e.target.value)}
        />
        <br />
        <br />
        <input
          type="password"
          placeholder="Senha"
          onChange={(e) => setSenha(e.target.value)}
        />
        <br />
        <button>Entrar</button>
      </form>
      <p>{msg}</p>
    </>
  );
};

export default Form;
