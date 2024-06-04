import React from "react";
import "./Header.css";
import {GiMushroomHouse} from "react-icons/gi";

export const Header = ({ onClick }) => {
    const Icon = GiMushroomHouse;

    return (
        <div className={"header"}>
            <button className="icon-button" onClick={onClick} aria-label="Home">
                <Icon size={60} className="icon" />
            </button>
            <h1>SmartHome</h1>
        </div>
    );
}