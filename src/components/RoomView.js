import React from 'react';
import RoomList from './RoomList';

const RoomView = ({ rooms, onRoomSelect, onBack }) => {
    return (
        <div className="room-view">
            <div className="room-header">
                <h2>Rooms</h2>
                <button className="back-button" onClick={onBack}>
                    Back
                </button>
            </div>
            <RoomList rooms={rooms} onRoomSelect={onRoomSelect} />
        </div>
    );
};

export default RoomView;
