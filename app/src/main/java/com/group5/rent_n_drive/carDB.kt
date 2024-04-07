package com.group5.rent_n_drive

data class Car(val id: Int, val name: String, val type: String, val imageUrl: String)

val cars = listOf(
    Car(1, "Car A", "Compact", "https://example.com/car_a.jpg"),
    Car(2, "Car B", "Sport", "https://example.com/car_b.jpg"),
    // Add more cars as needed
)
