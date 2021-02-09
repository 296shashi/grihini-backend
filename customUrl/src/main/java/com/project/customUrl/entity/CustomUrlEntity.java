package com.project.customUrl.entity;


import com.project.customUrl.model.CustomUrl;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "custom_url")
public class CustomUrlEntity extends CustomUrl {
}

