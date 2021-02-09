use grihini;
CREATE TABLE `merchant_info` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` long default NULL,
  `username` varchar(50),
  `password` varchar(50),
  `oldpassword` varchar(50),
  `basic_detail_id` int not null,
  `onboarding_data_id` int not null,
  `address_id` int not null,
  `cuisine` ENUM('BIHARI','BENGALI','SOUTH_INDIAN','NORTH_INDIAN')  not null,
  `merchant_type` varchar(50),
  `food_category` int default null,
  `created_at` timestamp not null default current_timestamp,
  `updated_at` timestamp not null default current_timestamp on update current_timestamp,
  PRIMARY KEY (id)
);
CREATE TABLE `address` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `address_line1` varchar(255) default null,
  `address_line2` varchar(255) default null,
  `address_line3` varchar(255) default null,
  `city` varchar(50) default null,
  `state` varchar(50) default null,
  `pincode` varchar(10) default null,
  `latitute` double default null,
  `longitude` double default null,
  `status` ENUM('ACTIVE','INACTIVE') not null,
  `created_at` timestamp not null default current_timestamp,
  `updated_at` timestamp not null default current_timestamp on update current_timestamp,
  PRIMARY KEY (id)
);

CREATE TABLE `basic_details` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) not null,
  `last_name` varchar(50) not null,
  `mobile_no` varchar(20) not null,
  `email` varchar (50) not null,
  `gender` varchar(10) not null,
  `dob` date not null,
  `image_url` varchar(255) default null,
  `document_url` varchar (255) default null,
  `status` ENUM('ACTIVE','INACTIVE') not null,
  `created_at` timestamp not null default current_timestamp,
  `updated_at` timestamp not null default current_timestamp on update current_timestamp,
  PRIMARY KEY (id)
);

CREATE TABLE `admin`
(
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) not null,
  `lastName` varchar(50) not null,
  `username` varchar(20) not null,
  `password` varchar (50) not null,
  `email` varchar(20) not null,
  `dob` date not null,
  `gender` varchar(10) not null,
  `mobileNo` varchar(15) default null,
  `address` varchar (255) default null,
  `created_at` timestamp not null default current_timestamp,
  `updated_at` timestamp not null default current_timestamp on update current_timestamp,
  PRIMARY KEY (id)
);

CREATE TABLE `onboarding_info` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `panel_id` long NOT NULL,
  `request` json default null,
  `error` varchar(30) default null,
  `error_code` int default null,
  `status` ENUM('INIT','PENDING','APPROVED','REJECTED','BLACKLIST') not null,
  `created_at` timestamp not null default current_timestamp,
  `updated_at` timestamp not null default current_timestamp on update current_timestamp,
  PRIMARY KEY (id)
);

CREATE TABLE `menu_info` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `merchant_id` long,
  `items` JSON,
  `price` decimal not null,
  `type` varchar(30),
  `status` int not null,
  `cusine` int not null,
  `image_url` varchar(255),
  `created_at` timestamp not null default current_timestamp,
  `updated_at` timestamp not null default current_timestamp on update current_timestamp,
  PRIMARY KEY (id)
);






