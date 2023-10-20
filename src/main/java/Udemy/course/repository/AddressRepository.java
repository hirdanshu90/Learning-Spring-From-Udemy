package Udemy.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Udemy.course.entity.Address;
import Udemy.course.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import Udemy.course.entity.Student;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
