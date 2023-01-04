import './App.css';
// third party, found in package.json
import { Link, Switch, Route } from 'react-router-dom'; 

import Home from './Home'; // relative paths
import SignUp from './SignUp'; 
import Payment from './Payment';
function App() {
  return (
    <div class="navbar">
      <ul>
        <li><Link to="/">Home</Link></li>
        <li><Link to="/sign-up">Sign Up</Link></li>
        <li><Link to="payment">Payment</Link></li>
        </ul>
        <Switch>
          <Route path="/sign-up" component={SignUp} />
          <Route path="/payment" component={Payment}/>
          <Home/>
         
        </Switch>
      
    </div>
  );
}

export default App;