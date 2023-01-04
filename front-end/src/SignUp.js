// Step 1 : Import React
import React from 'react'; // single quotes are preference
import './SignUp.css';
//Step 2 : Create a component function that returns an element
const SignUp = () => {
    const [username, setUsername] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [result, setResult] = React.useState(null);
    const [isLoading, setIsLoading] = React.useState(false);
    const handleSignUp = () => {
        setIsLoading(true);
        console.log('User clicked sign up', username, password)
        const body = {
            username: username,
            password: password,

        };
        // make an http call to java
        const settings = {
            method: 'post',
            body: JSON.stringify(body),
        };

    fetch('/api/sign-up', settings) // built in
    .then(response => response.json())
    .then(data => setResult(data))
    .catch(console.log); //async try/catch

    setIsLoading(false);
};
// result == null ? 1 : 2
return result !== null && result.isSuccess ? 
    <div class="signedUp">
        Welcome {username}!
    </div>:

    <div class="container">
        
        <h1 class="signUp">SignUp</h1>
        <p>Please enter a username and <br/>password to create an account.</p>
        <hr/>
        <div>
        <label for="username"><b>Username: </b></label>
        <div class="usernameTaken">{(result !== null && !result.isSuccess) && <div>{result.message}</div>}</div>
        <input type="text" placeholder="Enter Username" name="username" required
            value={username} 
            onChange={e => setUsername(e.target.value)}
            required/>
        </div>
        <div>
            <label for="psw"><b>Password:</b></label>
            <input class="signUp-input" type="password" placeholder="Enter Password" name="psw"
            value={password}
            onChange={e => setPassword(e.target.value)}
            required/>
        </div>
        <div class="signUpButton">
            <button class="" disabled={isLoading} onClick={handleSignUp}>Sign Up</button>
        </div>
        
    </div>
};

//Step 3 
export default SignUp; //equivallent to "public" in java