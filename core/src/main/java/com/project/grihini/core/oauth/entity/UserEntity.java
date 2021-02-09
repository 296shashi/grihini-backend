package com.project.grihini.core.oauth.entity;

import com.project.grihini.core.oauth.model.User;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity extends User {
}
