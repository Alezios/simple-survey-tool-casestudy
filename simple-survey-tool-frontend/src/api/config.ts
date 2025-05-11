// API Configuration
export const API_CONFIG = {
  BASE_URL: '/backend/api/', // Nginx will proxy this to the backend server to avoid CORS issues
  TIMEOUT: 10000,
  HEADERS: {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
  }
};