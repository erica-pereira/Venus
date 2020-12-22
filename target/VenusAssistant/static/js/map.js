function initMap() {
    // The location of Uluru
    const uluru = { lat: 40.644, lng: -8.6454 };
    // The map, centered at Uluru
    const map = new google.maps.Map(document.getElementById("map"), {
        zoom: 4,
        center: uluru,
    });
const marker = new google.maps.Marker({
    position: uluru,
    map: map,
});
}
