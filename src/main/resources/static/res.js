document.addEventListener("DOMContentLoaded", function() {
    fetch("/restaurant")
        .then(response => response.json())
        .then(data => displayRestaurantData(data))
        .catch(error => console.error("Error fetching restaurants:", error));
});

function displayRestaurantData(restaurants) {
    const restaurantDataElement = document.getElementById("restaurantData");

    if (restaurants.length === 0) {
        restaurantDataElement.innerHTML = "<p>No restaurants found.</p>";
        return;
    }

    let restaurantHTML = "<ul>";
    restaurants.forEach(restaurant => {
        restaurantHTML += `<li>Name: ${restaurant.name}</li>`;
        restaurantHTML += `<li>Address: ${restaurant.address}</li>`;
        restaurantHTML += `<li>Status: ${restaurant.stt}</li>`;
        restaurantHTML += "<br>";
    });
    restaurantHTML += "</ul>";

    restaurantDataElement.innerHTML = restaurantHTML;
}
