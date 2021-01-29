package com.example.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Book {

  private int id;
  private String title;
  private double price;
}