import React from 'react';
import './ReadingList.css';

const ReadingList = ({ deviceID, readings, startDate, endDate, onBack }) => {
  return (
    <div className="readings-list">
      <div className="readings-list-header">
        <h2>Device ID: {deviceID}</h2>
        <button className="back-button" onClick={onBack}>Back</button>
      </div>
      <div className="readings-list-info">
        <p><strong>Start Date:</strong> {startDate}</p>
        <p><strong>End Date:</strong> {endDate}</p>
        <table className="readings-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Type</th>
              <th>Timestamp</th>
            </tr>
          </thead>
          <tbody>
            {readings.map((reading) => (
              <tr key={reading.id} className="reading-item">
                <td>{reading.id}</td>
                <td>{reading.sensorType}</td>
                <td>{reading.timestamp}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ReadingList;
