import React from 'react';
import SunInfoCard from './components/SunInfoCard';
import TemperatureCard from './components/TemperatureCard';
import MainCard from './MainCard';
import './Home.css'; // Para estilos

const Home = () => {
  return (
    <div className="home-container">
      <h1>SmartHome</h1>
      <div className="cards-container">
        <div className="left-card">
          <MainCard />
        </div>
        <div className="right-cards">
          <SunInfoCard type="sunset" />
          <SunInfoCard type="sunrise" />
          <TemperatureCard />
        </div>
      </div>
    </div>
  );
}

export default Home;
