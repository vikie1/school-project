import { Navigate, Route, Routes} from 'react-router-dom';
import './App.css';
import { HomePage } from './pages/HomePage';

function App() {
  return (
    <div className="App">

      <Routes>
        
        <Route path='/' element={<HomePage />} />
        <Route path='/home' element={<HomePage />} />

        <Route path='*' element={<Navigate to='/home' replace/>} />
      </Routes>

    </div>
  );
}

export default App;
