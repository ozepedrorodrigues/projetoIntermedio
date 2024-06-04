import React, { useState, useEffect } from 'react';
import { FaTemperatureHigh } from 'react-icons/fa';
import './TemperatureCard.css';

const TemperatureCard = () => {
    const [temperature, setTemperature] = useState(null);

    useEffect(() => {
        const fetchTemperature = async () => {
            try {
                const response = await fetch('http://10.9.24.170:8080/InstantaneousTemperature?groupNumber=5&hour=15');

                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                const data = await response.json();

                if (data.hasOwnProperty('measurement') && !isNaN(data.measurement)) {
                    setTemperature(data.measurement);
                    console.log('Temperature fetched successfully:', data.measurement);
                } else {
                    console.error('Failed to fetch temperature: Invalid measurement', data.info);
                }
            } catch (error) {
                console.error('Error fetching temperature:', error);
            }
        };

        fetchTemperature();
    }, []); // Empty dependency array ensures this runs only once when the component mounts

    const getTemperatureColor = (temp) => {
        if (temp === null) return '#c2c2c2'; // Default color if temperature is not yet available
        return temp > 30 ? '#FF6347' : temp > 15 ? '#FFD700' : '#1E90FF';
    };

    return (
        <div className="card temperature-card" style={{ backgroundColor: getTemperatureColor(temperature) }}>
            <FaTemperatureHigh size={60} className="icon" />
            <div className="text-container">
                <h2>Current Temperature</h2>
                <p>{temperature !== null ? `${temperature}°C` : 'Loading...'}</p>
            </div>
        </div>
    );
}

export default TemperatureCard;
