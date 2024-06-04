import React from "react";
import "./Header.css";
import {FaHome} from "react-icons/fa";

export const Header = ({ onClick }) => {
    const Icon = FaHome;

    return (
        <div className={"header"}>
            <button className="icon-button" onClick={onClick} aria-label="Home">
                <Icon size={60} className="icon" />
            </button>
            <h1>SmartHome</h1>
        </div>
    );
}