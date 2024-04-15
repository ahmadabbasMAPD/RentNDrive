package com.group5.rent_n_drive
/*
* Made by Ahmad Abbas, Rahul Edirisinghe, Calist Dsouza, Saurav Gautam for MAPD721 Project.
* Class notes, and Other references and online resources used to help write code given below.
* */

data class Car(val id: Int, val name: String, val type: String, val imageUrl: String)

val cars = listOf(
    Car(1, "Honda Civic", "Compact", "https://thumbor.forbes.com/thumbor/fit-in/960x600/https://www.forbes.com/wheels/wp-content/uploads/2023/03/2023_Honda_Civic_Gallery1.jpg"),
    Car(2, "Toyota Supra", "Sport", "https://imageio.forbes.com/specials-images/imageserve/6064af8e13292f4369b15ae6/2021-Toyota-Supra/960x0.jpg?format=jpg&width=1440"),
    Car(3, "Audi A4", "Sedan", "https://www.topgear.com/sites/default/files/cars-car/image/2021/03/audiuk0002285520audi20a420avant.jpg"),
    Car(4, "BMW 3 Series", "Sedan", "https://www.topgear.com/sites/default/files/2022/09/1-BMW-3-Series.jpg"),
    Car(5, "Mercedes-Benz E-Class", "Sedan", "https://www.topgear.com/sites/default/files/2023/12/1%20Mercedes%20E-Class%20review.jpg"),
    Car(6, "Tesla Model 3", "Sedan", "https://upload.wikimedia.org/wikipedia/commons/9/91/2019_Tesla_Model_3_Performance_AWD_Front.jpg"),
    Car(7, "Chevrolet Impala", "Sedan", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/38/2014_Chevrolet_Impala_LTZ_3.6L_with_courtesy_plates%2C_front_6.1.19.jpg/280px-2014_Chevrolet_Impala_LTZ_3.6L_with_courtesy_plates%2C_front_6.1.19.jpg"),
    Car(8, "Ford Mustang", "Sport", "https://upload.wikimedia.org/wikipedia/commons/d/d1/2018_Ford_Mustang_GT_5.0.jpg"),
    Car(9, "Dodge Charger", "Sedan", "https://medias.fcacanada.ca/jellies/renditions/2023/800x510/CC23_LDDS48_2DH_PFB_APA_XXX_XXX_XXX.bc9edc51919be4cf32b1e62c07be198f.png"),
    Car(10, "Hyundai Elantra", "Sedan", "https://www.hyundaicanada.com/-/media/hyundai/showroom/2024/elantra/exterior_gallery/expanded/desktop_xl/img_001.jpg"),
    Car(11, "Nissan Altima", "Sedan", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/2023_Nissan_Altima_SV_in_Scarlet_Ember_Tintcoat%2C_Front_Left%2C_09-12-2023.jpg/1200px-2023_Nissan_Altima_SV_in_Scarlet_Ember_Tintcoat%2C_Front_Left%2C_09-12-2023.jpg"),
    Car(12, "Subaru Outback", "SUV", "https://hips.hearstapps.com/hmg-prod/images/2024-subaru-outback-wilderness-101-647e3552ec5ba.jpg?crop=0.721xw:0.810xh;0.103xw,0.190xh&resize=768:*"),
    Car(13, "Volkswagen Golf", "Compact", "https://upload.wikimedia.org/wikipedia/commons/8/8a/2020_Volkswagen_Golf_Style_1.5_Front.jpg"),
    Car(14, "Audi Q7", "SUV", "https://upload.wikimedia.org/wikipedia/commons/8/8b/2017_Audi_Q7_S_Line_Quattro_3.0_Front.jpg"),
    Car(15, "BMW X5", "SUV", "https://www.bmw.ca/content/dam/bmw/common/all-models/x-series/x5/2023/highlights/bmw-x-series-x5-sp-desktop.jpg"),
    Car(16, "Mercedes-Benz GLE", "SUV", "https://upload.wikimedia.org/wikipedia/commons/8/8b/Mercedes-Benz_GLE_350_d_4MATIC_AMG_Line_%28V_167%29_–_f_18042021.jpg"),
    Car(17, "Tesla Model X", "SUV", "https://i.vimeocdn.com/video/1533953944-2bdd92c230dc32770ceda32de016cf7e51511ceb1aae4b5f271e437178ccb550-d_640?f=webp"),
    Car(18, "Chevrolet Tahoe", "SUV", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/22/2022_Chevrolet_Tahoe_RST%2C_front_3.7.22.jpg/1200px-2022_Chevrolet_Tahoe_RST%2C_front_3.7.22.jpg"),
    Car(19, "Ford Explorer", "SUV", "https://hips.hearstapps.com/hmg-prod/images/2025-ford-explorer-st-110-65ba6d640cbb3.jpg?crop=0.744xw:0.906xh;0.171xw,0.0807xh&resize=768:*"),
    Car(20, "Dodge Durango", "SUV", "https://hips.hearstapps.com/hmg-prod/images/2024-dodge-durango-102-64f1b07abf619.jpg?crop=0.702xw:0.790xh;0.195xw,0.185xh&resize=768:*"),
    )