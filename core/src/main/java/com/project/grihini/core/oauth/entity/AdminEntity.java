package com.project.grihini.core.oauth.entity;

import com.project.grihini.core.oauth.model.Admin;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class AdminEntity extends Admin {
}
