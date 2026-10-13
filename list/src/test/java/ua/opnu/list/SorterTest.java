package ua.opnu.list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SorterTest {
    private List<Student> students() {
        return new ArrayList<>(Arrays.asList(
                new Student("Борис", "Іванов", 75),
                new Student("Петро", "Петренко", 92),
                new Student("Сергій", "Сергієнко", 61),
                new Student("Григорій", "Сковорода", 88)));
    }

    @Test
    void namesInBothDirections() {
        List<Student> students = students();
        students.sort(new NameSorter(true));
        assertEquals("Борис", students.get(0).getName());
        assertEquals("Григорій", students.get(1).getName());
        assertEquals("Петро", students.get(2).getName());
        assertEquals("Сергій", students.get(3).getName());
        students.sort(new NameSorter(false));
        assertEquals("Сергій", students.get(0).getName());
        assertEquals("Петро", students.get(1).getName());
        assertEquals("Григорій", students.get(2).getName());
        assertEquals("Борис", students.get(3).getName());
    }

    @Test
    void lastNamesInBothDirections() {
        List<Student> students = students();
        students.sort(new LastNameSorter(true));
        assertEquals("Іванов", students.get(0).getLastName());
        assertEquals("Петренко", students.get(1).getLastName());
        assertEquals("Сергієнко", students.get(2).getLastName());
        assertEquals("Сковорода", students.get(3).getLastName());
        students.sort(new LastNameSorter(false));
        assertEquals("Сковорода", students.get(0).getLastName());
        assertEquals("Сергієнко", students.get(1).getLastName());
        assertEquals("Петренко", students.get(2).getLastName());
        assertEquals("Іванов", students.get(3).getLastName());
    }

    @Test
    void marksInBothDirections() {
        List<Student> students = students();
        students.sort(new MarkSorter(true));
        assertEquals(61, students.get(0).getAvgMark(), 0.000001);
        assertEquals(75, students.get(1).getAvgMark(), 0.000001);
        assertEquals(88, students.get(2).getAvgMark(), 0.000001);
        assertEquals(92, students.get(3).getAvgMark(), 0.000001);
        students.sort(new MarkSorter(false));
        assertEquals(92, students.get(0).getAvgMark(), 0.000001);
        assertEquals(88, students.get(1).getAvgMark(), 0.000001);
        assertEquals(75, students.get(2).getAvgMark(), 0.000001);
        assertEquals(61, students.get(3).getAvgMark(), 0.000001);
    }

    @Test
    void equalKeysAndFractionalMarks() {
        Student first = new Student("Андрій", "Бондар", 88.5);
        Student second = new Student("Андрій", "Бондар", 88.5);
        assertEquals(0, new NameSorter(true).compare(first, second));
        assertEquals(0, new LastNameSorter(false).compare(first, second));
        assertEquals(0, new MarkSorter(true).compare(first, second));
        second.setAvgMark(88.75);
        assertTrue(new MarkSorter(true).compare(first, second) < 0);
        assertTrue(new MarkSorter(false).compare(first, second) > 0);
    }
}
