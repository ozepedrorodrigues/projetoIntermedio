import React from 'react';
import { FaSun, FaMoon } from 'react-icons/fa';
import './SunInfoCard.css';

const SunInfoCard = ({ type }) => {
  const isSunset = type === 'sunset';
  const title = isSunset ? 'Sunset' : 'Sunrise';
  const time = isSunset ? '18:45' : '06:30'; // Valores fictícios
  const Icon = isSunset ? FaMoon : FaSun;

  return (
    <div className="card sun-info-card">
      <Icon size={60} className="icon" />
      <div className="text-container">
        <h2>{title}</h2>
        <p>{time}</p>
      </div>
    </div>
  );
}

export default SunInfoCard;
