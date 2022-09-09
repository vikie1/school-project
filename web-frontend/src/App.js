import { Navigate, Route, Routes} from 'react-router-dom';
import './App.css';
import { HomePage } from './pages/HomePage';
import { ReportPage } from './pages/ReportPage';
import { Statistics } from './pages/Statistics';

function App() {
  return (
    <div className="App">

      <Routes>
        
        <Route path='/' element={<HomePage />} />
        <Route path='/home' element={<HomePage />} />
        <Route path='/report' element={<ReportPage />} />
        <Route path='/analytics' element={<Statistics />} />

        <Route path='*' element={<Navigate to='/home' replace/>} />
      </Routes>

    </div>
  );
}

export default App;
