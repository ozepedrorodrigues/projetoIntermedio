import React from 'react';
import { FaTemperatureHigh } from 'react-icons/fa';
import './TemperatureCard.css';

const TemperatureCard = () => {
  const temperature = 22; // Valor fictício
  const temperatureColor = temperature > 30 ? '#FF6347' : temperature > 15 ? '#FFD700' : '#1E90FF';

  return (
    <div className="card temperature-card" style={{ backgroundColor: temperatureColor }}>
      <FaTemperatureHigh size={60} className="icon" />
      <div className="text-container">
        <h2>Current Temperature</h2>
        <p>{temperature}°C</p>
      </div>
    </div>
  );
}

export default TemperatureCard;
