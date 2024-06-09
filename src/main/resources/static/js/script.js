console.log("Inside theme script file");

let currentTheme = getTheme();

document.addEventListener("DOMContentLoaded", () => {
    document.querySelector("#light-dark-theme span").textContent = currentTheme === 'light' ? ' Dark' : ' Light';
    changeTheme(currentTheme);
  });

function changeTheme(currentTheme){
    // Accessing the css-class property of html element
    document.querySelector("html").classList.add(currentTheme);
    
    let themechangeButton = document.querySelector("#light-dark-theme");
    themechangeButton.addEventListener("click", ()=> {
        let oldEle = currentTheme;
        
        if(currentTheme === 'light'){
            currentTheme = 'dark';
        }
        else{
            currentTheme = 'light';
        }
        
        //changing the theme
        setTheme(currentTheme);


        //removing old property
        document.querySelector("html").classList.remove(oldEle);

        //adding new property
        document.querySelector("html").classList.add(currentTheme);
        console.log(currentTheme);

        let newText = currentTheme === 'light' ? ' Dark' : ' Light';
        console.log(`Current text: ${newText}`);
        themechangeButton.querySelector("span").textContent = newText;
    });
    


}


function setTheme(theme){
    localStorage.setItem("theme",theme);
}

function getTheme(){
    if(localStorage.getItem("theme")){
        return localStorage.getItem("theme");
    }
    return "white";
}



// Password validation
console.log("Inside password validation script file");

var myInput = document.getElementById("psw");
var letter = document.getElementById("letter");
var capital = document.getElementById("capital");
var number = document.getElementById("number");
var specialChar = document.getElementById("spchars");
var length = document.getElementById("length");

// When the user clicks on the password field, show the message box
myInput.onfocus = function() {
    document.getElementById("message").style.display = "block";
}

// When the user clicks outside of the password field, hide the message box
myInput.onblur = function() {
    document.getElementById("message").style.display = "none";
}

// When the user starts to type something inside the password field
myInput.onkeyup = function() {
    // Validate lowercase letters
    var lowerCaseLetters = /[a-z]/g;
    if(myInput.value.match(lowerCaseLetters)) {  
    letter.classList.remove("invalid");
    letter.classList.add("valid");
    } else {
    letter.classList.remove("valid");
    letter.classList.add("invalid");
    }
    
    // Validate capital letters
    var upperCaseLetters = /[A-Z]/g;
    if(myInput.value.match(upperCaseLetters)) {  
    capital.classList.remove("invalid");
    capital.classList.add("valid");
    } else {
    capital.classList.remove("valid");
    capital.classList.add("invalid");
    }

    // Validate numbers
    var numbers = /[0-9]/g;
    if(myInput.value.match(numbers)) {  
    number.classList.remove("invalid");
    number.classList.add("valid");
    } else {
    number.classList.remove("valid");
    number.classList.add("invalid");
    }
    
    // Validate length
    if(myInput.value.length >= 8) {
    length.classList.remove("invalid");
    length.classList.add("valid");
    } else {
    length.classList.remove("valid");
    length.classList.add("invalid");
    }

    // Validate special characters
    var specialCharacters = /[!@#$%^&*]/g;
    if(myInput.value.match(specialCharacters)) {
        specialChar.classList.remove("invalid");
        specialChar.classList.add("valid");
    } else {
        specialChar.classList.remove("valid");
        specialChar.classList.add("invalid");
    }
}

function myFunction() {
    console.log("Inside myFunction");

    try{
        var x = document.getElementById("password");
        if (x.type === "password") {
          x.type = "text";
        } else {
          x.type = "password";
        }
    }
    catch(e){
        console.log(e);
    }

    try{
        var x = document.getElementById("psw");
        if (x.type === "password") {
          x.type = "text";
        } else {
          x.type = "password";
        }
    }
    catch(e){
        console.log(e);
    }
  }

  function startPayment() {
	console.log("inside start payment");
	let amount = document.getElementById("amt").value;
	console.log(amount);

	if(amount == null ||amount == '')
	{
		Swal.fire({
			title: "Error",
			text: "Amount field should not be null!",
			icon: "error"
		});
		return;
	}

	$.ajax({
		url: '../payment/create_order',
		data: JSON.stringify({ amount: amount , info : 'order_request'}),
		contentType: 'application/json',
		type: 'POST',
		dataType: 'json',
		success: function(response) {
			console.log(response);


			let options = {
				key: "rzp_test_oyYGW2jJpcIAQn",
				amount: response.amount,
				currency: "INR",
				name: "Smart Contact Manager",
				description: "Help us to improve by donating",
				image:
					"https://sugermint.com/wp-content/uploads/2022/01/Razorpay-Startup-Story.jpg",
				order_id:response.id,


				handler:function (response){
					console.log(response.razorpay_payment_id);
					console.log(response.razorpay_order_id);
					console.log(response.razorpay_signature);

					updatePayment(response.razorpay_payment_id,response.razorpay_order_id,'paid')

					console.log("Payment successful");
					Swal.fire({
						title: "Success",
						text: "Payment Success!",
						icon: "success"
					});
				},

				prefill: {
					"name": "",
					"email": "",
					"contact": ""
				},
				notes: {
					"address": "Razorpay Corporate Office"
				},
				theme: {
					"color": "#3399cc"
				}
			};

			let rzp = new Razorpay(options);

			rzp.on('payment.failed', function (response){
				console.log(response.error.code);
				console.log(response.error.description);
				console.log(response.error.source);
				console.log(response.error.step);
				console.log(response.error.reason);
				console.log(response.error.metadata.order_id);
				console.log(response.error.metadata.payment_id);

				Swal.fire({
					title: "Error",
					text: "Payment Failed!",
					icon: "error"
				});
			});

			rzp.open();
		},
		error: function(error) {
			console.log("Something went wrong");
		}
	});

};

function updatePayment(payment_id,order_id,statusx)
{
	$.ajax({
		url: '../user/payment/update-order',
		data: JSON.stringify({payment_id : payment_id, order_id : order_id,statusx : statusx}),
		contentType: 'application/json',
		type: 'POST',
		dataType: 'json',
		success:function (error){
			console.log(error);
			Swal.fire({
				title: "Success",
				text: "Payment Done..Thank You for your support!",
				icon: "success"
			});
		}
	})
}