import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import './Navigation.css';

const Navigation = () => {
  const { isAuthenticated, logout } = useAuth();
  const location = useLocation();

  const isActive = (path) => {
    return location.pathname === path ? 'active' : '';
  };

  return (
    <nav className="navigation">
      <div className="nav-container">
        <div className="nav-brand">
          <Link to="/">Auth System</Link>
        </div>
        
        <div className="nav-links">
          {!isAuthenticated ? (
            <>
              <Link to="/" className={isActive('/')}>
                Home
              </Link>
              <Link to="/login" className={isActive('/login')}>
                Login
              </Link>
              <Link to="/register" className={isActive('/register')}>
                Register
              </Link>
            </>
          ) : (
            <>
              <Link to="/dashboard" className={isActive('/dashboard')}>
                Dashboard
              </Link>
              <Link to="/profile" className={isActive('/profile')}>
                Profile
              </Link>
              <button onClick={logout} className="nav-logout-btn">
                Logout
              </button>
            </>
          )}
        </div>
      </div>
    </nav>
  );
};

export default Navigation;
