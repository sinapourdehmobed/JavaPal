// Step 1 : Import React
import React from 'react'; // single quotes are preference
import './Payment.css';
import { Link, Switch, Route } from 'react-router-dom'; 
//Step 2 : Create a component function that returns an element
 const Payment = () => {
     const [toUser, setToUser] = React.useState('');
//   const [fromUser, setFromUser] = React.useState('');
     const [amount, setAmount] = React.useState(0);
     const [type, setType] = React.useState('');
     const [result, setResult] = React.useState(null);
     const handlePayment = () => {
            console.log('User entered: ', toUser, type, amount)
            const payUser = {
                toUser: toUser,
            //  fromUser: fromUser,
                amount: amount,
                type: type
    
            };
            // make an http call to java
            const settings = {
                method: 'post',
                body: JSON.stringify(payUser),
            };
            
        fetch(type, settings) // built in
        .then(response => response.json())
        .then(data => setResult(data))
        .catch(console.log); //async try/catch
        
      }
      
    return (
            <div class="v38_109">
                <div class="v35_6">
                    <div class="v35_16"/>
                    
                    <div class="v35_22">
                        <div class="v35_12"></div>
                        <span class="v35_13">Send another Payment?</span>
                        
                    </div>
                    
                    <div class="v35_25"></div>
                    <span class="v36_26">Send Payment</span>
                    <div class="v36_29">
                        <div class="v36_27"></div>
                        <input value={toUser}
                                onChange={e => setToUser(e.target.value)} 
                                type="text"
                                class="v36_28" 
                                placeholder="Enter Recipient Username"/>
                    </div>
                    <div class="v36_31">
                        <div class="v36_32"></div>
                        <input value={amount} 
                                onChange={e => setAmount(e.value)}
                                type="text" 
                                class="v36_33" 
                                placeholder = "Enter Amount"/>
                    </div>
                    <div class="v36_34">
                        <div class="v36_35">            
                        <label for="Payment-Type">Payment type: </label>               
                        <select name="Payment-Type" id="Payment-Type">
                            
                            <option 
                                value="/makeCashPayment"
                                onChange={e => setType(e.target.value)}
                                required>
                                Cash 
                            </option>
                            <option 
                                value="/makeCreditCardPayment"
                                onChange={e => setType(e.target.value)}
                                required>
                                Credit Card
                            </option>
                        
                        </select>                         
                    </div>
                    </div>

                <button class="v38_49" onClick={handlePayment}>
                    <div class="v36_47"></div>
                    <span class="v36_48">Send</span>
                </button>
                <Link>                
                    <div class="v35_19">
                        <div class="v35_20"></div>
                        <span class="v35_21">PayDaddy</span>
                    </div>
                </Link>
                <span class="v38_80">By signing up, you agree to the 
                    <Link>Terms of Service</Link>
                    , and
                    <Link>Privacy Policy</Link><br/>
                     Questions?
                     <Link>Contact Us</Link>
                </span>
                <div class="name"/>
                <div class="name"/>
                </div>
            </div>
    );
};

//Step 3 
export default Payment; //equivallent to "public" in java