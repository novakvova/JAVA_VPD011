import React from 'react';
import logo from './logo.svg';
import './App.css';
import NotFoundPage from './components/notFound';
import HomePage from './components/home';
import { Route, Routes } from 'react-router-dom';
import DefaultLayout from './components/containers/default';
import LoginPage from './components/auth/login';

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<DefaultLayout/>}>
          <Route index element={<HomePage/>} />
          <Route path="login" element={<LoginPage/>} />

          <Route path="*" element={<NotFoundPage/>} />
        </Route>
      </Routes>
    </>
  );
}

export default App;
