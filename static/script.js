//document.getElementById("loginForm").addEventListener("submit", function(event) {
//  event.preventDefault(); // Prevent form submission
//
//  // Get form values
//  var username = document.getElementById("username").value;
//  var password = document.getElementById("password").value;
//
//  // Create an object to hold the login credentials
//  var credentials = {
//    userId: username,
//    password: password
//  };
//
//  // Send the login request to the server
//  fetch('http://localhost:8080/user/login', {
//    method: 'POST',
//    headers: {
//      'Content-Type': 'application/json'
//    },
//    body: JSON.stringify(credentials)
//  })
//  .then(function(response) {
//    if (response.ok) {
//      // Login successful, redirect to the desired page
//      window.location.href = '/index.html';
//    } else {
//      // Login failed, display an error message
//      console.error('Login failed:', response.statusText);
//    }
//  })
//  .catch(function(error) {
//    console.error('An error occurred:', error);
//  });
//});

document.addEventListener("DOMContentLoaded", function() {
  // DOMContentLoaded event - Fires when the initial HTML document has been completely loaded and parsed

  // Add event listeners

  // Login form submit event listener
  const loginForm = document.getElementById("loginForm");
  if (loginForm) {
    loginForm.addEventListener("submit", handleLoginFormSubmit);
  }

  // Logout button click event listener
  const logoutButton = document.getElementById("logoutButton");
  if (logoutButton) {
    logoutButton.addEventListener("click", handleLogoutButtonClick);
  }

  // Add button click event listener
  const addButton = document.getElementById("addButton");
  if (addButton) {
    addButton.addEventListener("click", handleAddButtonClick);
  }

  const reservationForm = document.getElementById("reservationForm");
    if (reservationForm) {
      reservationForm.addEventListener("click", handleReserveClick);
    }

  // Function to handle login form submission
  function handleLoginFormSubmit(event) {
    event.preventDefault();

    // Get form data and perform login logic
    // ...
    var username = document.getElementById("username").value;
      var password = document.getElementById("password").value;

      // Create an object to hold the login credentials
      var credentials = {
        userId: username,
        password: password
      };

      // Send the login request to the server
      fetch('/user/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
      })
      .then(function(response) {
        if (response.ok) {
          // Login successful, redirect to successful page
          window.location.href = '/booking.html';
        } else {
          // Login failed, display an error message
          console.error('Login failed:', response.statusText);
        }
      })
      .catch(function(error) {
        console.error('An error occurred:', error);
      });
  }

  // Function to handle logout button click
  function handleLogoutButtonClick(event) {
    event.preventDefault();

    // Perform logout logic
    // ...
  }

  // Function to handle add button click
  function handleAddButtonClick(event) {
    event.preventDefault();

    // Perform add button logic
    // ...
  }

  // ... Table reservation
  function handleReserveClick(event) {
      event.preventDefault();

      // Perform add button logic
      // ...
      var name = document.getElementById("name").value;
        var date = document.getElementById("date").value;
        var time = document.getElementById("time").value;
        var partySize = document.getElementById("partySize").value;

        // Create reservation object
        var reservation = {
          name: name,
          date: date,
          time: time,
          partySize: partySize
        };

        // Send reservation data to API
        fetch("/reservation", {
          method: "POST",
          headers: {
            "Content-Type": "application/json"
          },
          body: JSON.stringify(reservation)
        })
        .then(function(response) {
          return response.json();
        })
        .then(function(data) {
          // Display reservation result
          var resultDiv = document.getElementById("reservationResult");
          resultDiv.textContent = data.message;
        })
        .catch(function(error) {
          console.log("Error:", error);
        });
    }

});



