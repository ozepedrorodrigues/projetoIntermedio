import { FaSun, FaMoon } from 'react-icons/fa';
import './SunInfoCard.css';
import React, { useEffect, useState } from 'react';

const SunInfoCard = ({ type }) => {
  const isSunset = type === 'sunset';
  const title = isSunset ? 'Sunset' : 'Sunrise';
  const Icon = isSunset ? FaMoon : FaSun;

  const [time, setTime] = useState(isSunset ? '18:45' : '06:30');

  useEffect(() => {
    fetch('http://10.9.24.170:8080/SunriseSunset')
      .then(response => response.json())
      .then(data => {
        const fetchedTime = isSunset ? data.Sunset : data.Sunrise;
        setTime(fetchedTime);
      })
      .catch(error => {
        console.error('Error fetching time:', error);
      });
  }, [isSunset]);

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
