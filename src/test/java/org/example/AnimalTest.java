package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimalTest {
    @Test
    void subclassesHaveTheirOwnSounds() {
        Animal dog = new Dog("Рекс");
        Animal cat = new Cat("Мурчик");
        assertEquals("Гав-гав!", dog.makeSound());
        assertEquals("Мяу!", cat.makeSound());
        assertEquals("Рекс", dog.getName());
        assertEquals("Мурчик", cat.getName());
    }

    @Test
    void animalCanBeRenamed() {
        Animal animal = new Dog("Рекс");
        animal.setName("Барс");
        assertEquals("Барс", animal.getName());
        assertEquals("Гав-гав!", animal.makeSound());
    }
}
