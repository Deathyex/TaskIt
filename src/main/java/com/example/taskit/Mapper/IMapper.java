package com.example.taskit.Mapper;

public interface IMapper <In, Out>{
    public Out map(In in);
}
