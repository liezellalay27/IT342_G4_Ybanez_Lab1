import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Homepage.css';

function Homepage() {
  const navigate = useNavigate();

  return (
    <div className="homepage">
      {/* Hero Section */}
      <section className="hero-section">
        <div className="hero-content">
          <h1 className="hero-title">Welcome to UserAuth</h1>
          <p className="hero-subtitle">
            A secure and modern authentication system built with React and Spring Boot
          </p>
          <div className="hero-buttons">
            <button 
              className="btn btn-primary" 
              onClick={() => navigate('/register')}
            >
              Get Started
            </button>
            <button 
              className="btn btn-secondary" 
              onClick={() => navigate('/login')}
            >
              Sign In
            </button>
          </div>
        </div>
        <div className="hero-image">
          <div className="hero-illustration">
            <div className="illustration-circle"></div>
            <div className="illustration-dots"></div>
          </div>
        </div>
      </section>

      {/* Features Section */}
      <section className="features-section">
        <h2 className="section-title">Why Choose UserAuth?</h2>
        <div className="features-grid">
          <div className="feature-card">
            <div className="feature-icon">🔒</div>
            <h3>Secure Authentication</h3>
            <p>Industry-standard JWT token-based authentication with secure password encryption</p>
          </div>
          
          <div className="feature-card">
            <div className="feature-icon">⚡</div>
            <h3>Fast & Responsive</h3>
            <p>Built with React for lightning-fast performance and seamless user experience</p>
          </div>
          
          <div className="feature-card">
            <div className="feature-icon">👤</div>
            <h3>Profile Management</h3>
            <p>Easy-to-use profile management system to keep your information up to date</p>
          </div>
          
          <div className="feature-card">
            <div className="feature-icon">📱</div>
            <h3>Multi-Platform</h3>
            <p>Access from web and mobile devices with consistent experience across platforms</p>
          </div>
          
          <div className="feature-card">
            <div className="feature-icon">🚀</div>
            <h3>Modern Stack</h3>
            <p>Built with cutting-edge technologies: React, Spring Boot, and PostgreSQL</p>
          </div>
          
          <div className="feature-card">
            <div className="feature-icon">🎨</div>
            <h3>Beautiful UI</h3>
            <p>Clean and intuitive interface designed for the best user experience</p>
          </div>
        </div>
      </section>

      {/* CTA Section */}
      <section className="cta-section">
        <div className="cta-content">
          <h2>Ready to Get Started?</h2>
          <p>Join thousands of users who trust our authentication system</p>
          <button 
            className="btn btn-cta" 
            onClick={() => navigate('/register')}
          >
            Create Your Account
          </button>
        </div>
      </section>

      {/* Footer */}
      <footer className="homepage-footer">
        <p>&copy; 2026 UserAuth. All rights reserved.</p>
        <p className="footer-subtitle">IT342 - Web Development Project</p>
      </footer>
    </div>
  );
}

export default Homepage;
