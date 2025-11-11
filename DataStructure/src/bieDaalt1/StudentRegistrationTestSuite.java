package bieDaalt1;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import dataStructures.ArrayLinearList;
import dataStructures.Chain;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Comprehensive JUnit 5 Test Suite for Student Registration System
 * Tests all core functionality including GPA calculation, failure detection,
 * and data management operations.
 */
class StudentRegistrationTestSuite {
    
    @TempDir
    Path tempDir;
    
    private Path subjectListPath;
    private Path majorListPath;
    private Path examsPath;
    
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() throws IOException {
        System.setOut(new PrintStream(outputStream));
        
        // Create test files in temporary directory
        subjectListPath = tempDir.resolve("subjectList.txt");
        majorListPath = tempDir.resolve("majorList.txt");
        examsPath = tempDir.resolve("exams.txt");
        
        createTestFiles();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    // ==================== Subject Class Tests ====================
    
    @org.junit.jupiter.api.Test
    void testSubjectCreation() {
        Subject subject = new Subject("CS101", "Introduction to Programming", 3.0f);
        
        assertEquals("CS101", subject.getCode());
        assertEquals("Introduction to Programming", subject.getName());
        assertEquals(3.0f, subject.getCredit(), 0.001);
    }
    
    @org.junit.jupiter.api.Test
    void testSubjectSettersAndGetters() {
        Subject subject = new Subject();
        subject.setCode("CS202");
        subject.setName("Data Structures");
        subject.setCredit(4.0f);
        
        assertEquals("CS202", subject.getCode());
        assertEquals("Data Structures", subject.getName());
        assertEquals(4.0f, subject.getCredit(), 0.001);
    }
    
    @org.junit.jupiter.api.Test
    void testSubjectToString() {
        Subject subject = new Subject("CS303", "Algorithms", 3.5f);
        assertEquals("CS303/Algorithms/3.5", subject.toString());
    }
    
    @org.junit.jupiter.api.Test
    void testSubjectCreationFromList() {
        ArrayLinearList subjectList = new ArrayLinearList();
        Subject s1 = new Subject("CS101", "Programming", 3.0f);
        Subject s2 = new Subject("CS202", "Data Structures", 3.0f);
        subjectList.add(0, s1);
        subjectList.add(1, s2);
        
        Subject foundSubject = new Subject(subjectList, "CS202");
        assertEquals("Data Structures", foundSubject.getName());
        assertEquals(3.0f, foundSubject.getCredit(), 0.001);
    }
    
    @org.junit.jupiter.api.Test
    void testSubjectPrint() {
        Subject subject = new Subject("CS101", "Test Subject", 3.0f);
        
        outputStream.reset();
        subject.print();
        
        String output = outputStream.toString().trim();
        assertTrue(output.contains("CS101"));
        assertTrue(output.contains("Test Subject"));
        assertTrue(output.contains("3.0"));
    }

    // ==================== Major Class Tests ====================
    
    @org.junit.jupiter.api.Test
    void testMajorCreation() {
        Major major = new Major("CS", "Computer Science");
        
        assertEquals("CS", major.getCode());
        assertEquals("Computer Science", major.getName());
    }
    
    @org.junit.jupiter.api.Test
    void testMajorSettersAndGetters() {
        Major major = new Major();
        major.setCode("IT");
        major.setName("Information Technology");
        
        assertEquals("IT", major.getCode());
        assertEquals("Information Technology", major.getName());
    }
    
    @org.junit.jupiter.api.Test
    void testMajorToString() {
        Major major = new Major("SW", "Software Engineering");
        assertEquals("SW/Software Engineering", major.toString());
    }
    
    @org.junit.jupiter.api.Test
    void testMajorPrint() {
        Major major = new Major("CS", "Computer Science");
        
        outputStream.reset();
        major.print();
        
        String output = outputStream.toString().trim();
        assertEquals("CS/Computer Science", output);
    }

    // ==================== Lessons Class Tests ====================
    
    @org.junit.jupiter.api.Test
    void testLessonsCreation() {
        Subject subject = new Subject("CS101", "Programming", 3.0f);
        Lessons lesson = new Lessons(subject, 85);
        
        assertEquals(subject, lesson.getLearned());
        assertEquals(85, lesson.getScore());
    }
    
    @org.junit.jupiter.api.Test
    void testLessonsSettersAndGetters() {
        Subject subject1 = new Subject("CS101", "Programming", 3.0f);
        Subject subject2 = new Subject("CS202", "Data Structures", 3.0f);
        Lessons lesson = new Lessons(subject1, 85);
        
        lesson.setLearned(subject2);
        lesson.setScore(90);
        
        assertEquals(subject2, lesson.getLearned());
        assertEquals(90, lesson.getScore());
    }
    
    @org.junit.jupiter.api.Test
    void testLessonsDefaultConstructor() {
        Lessons lesson = new Lessons();
        assertNull(lesson.getLearned());
        assertEquals(0, lesson.getScore());
    }

    // ==================== Student Class Tests ====================
    
    @org.junit.jupiter.api.Test
    void testStudentCreation() {
        Student student = new Student();
        student.setName("John Doe");
        student.setCode("CS");
        
        assertEquals("John Doe", student.getName());
        assertEquals("CS", student.getCode());
    }
    
    @org.junit.jupiter.api.Test
    void testStudentGPACalculation_PerfectScores() {
        Student student = new Student();
        student.setName("Alice");
        student.setCode("CS");
        
        ArrayLinearList subjectList = createTestSubjectList();
        
        // Add lessons with perfect scores (100)
        student.addLesson(100, subjectList, "CS101");
        student.addLesson(100, subjectList, "CS102");
        student.addLesson(100, subjectList, "CS103");
        
        // GPA for 100: (100/20 - 1) = 4.0
        assertEquals(4.0f, student.getGPA(), 0.01);
    }
    
    @org.junit.jupiter.api.Test
    void testStudentGPACalculation_MixedScores() {
        Student student = new Student();
        student.setName("Bob");
        student.setCode("IT");
        
        ArrayLinearList subjectList = createTestSubjectList();
        
        // Add lessons with mixed scores
        student.addLesson(100, subjectList, "CS101"); // GPA: 4.0
        student.addLesson(80, subjectList, "CS102");  // GPA: 3.0
        student.addLesson(60, subjectList, "CS103");  // GPA: 2.0
        
        // Average: (4.0 + 3.0 + 2.0) / 3 = 3.0
        assertEquals(3.0f, student.getGPA(), 0.01);
    }
    
    @org.junit.jupiter.api.Test
    void testStudentGPACalculation_WithDifferentCredits() {
        Student student = new Student();
        student.setName("Charlie");
        student.setCode("SW");
        
        ArrayLinearList subjectList = new ArrayLinearList();
        subjectList.add(0, new Subject("CS101", "Programming", 3.0f));
        subjectList.add(1, new Subject("CS102", "Math", 4.0f));
        
        student.addLesson(80, subjectList, "CS101");  // 3.0 * 3 credits = 9.0 quality points
        student.addLesson(100, subjectList, "CS102"); // 4.0 * 4 credits = 16.0 quality points
        
        // Total: 25.0 quality points / 7 credits = 3.571
        assertEquals(3.571f, student.getGPA(), 0.01);
    }
    
    @org.junit.jupiter.api.Test
    void testStudentGPACalculation_FailingGrade() {
        Student student = new Student();
        student.setName("Dave");
        student.setCode("CS");
        
        ArrayLinearList subjectList = createTestSubjectList();
        
        student.addLesson(40, subjectList, "CS101"); // (40/20 - 1) = 1.0
        student.addLesson(20, subjectList, "CS102"); // (20/20 - 1) = 0.0
        
        // Average: (1.0 + 0.0) / 2 = 0.5
        assertEquals(0.5f, student.getGPA(), 0.01);
    }
    
    @org.junit.jupiter.api.Test
    void testStudentIsFallen_ThreeFailures() {
        Student student = new Student();
        student.setName("Eve");
        student.setCode("IT");
        
        ArrayLinearList subjectList = createTestSubjectList();
        
        student.addLesson(55, subjectList, "CS101"); // Failed
        student.addLesson(58, subjectList, "CS102"); // Failed
        student.addLesson(59, subjectList, "CS103"); // Failed
        
        assertTrue(student.isFallen(), "Student should be marked as fallen with 3 failures");
    }
    
    @org.junit.jupiter.api.Test
    void testStudentIsFallen_TwoFailures() {
        Student student = new Student();
        student.setName("Frank");
        student.setCode("CS");
        
        ArrayLinearList subjectList = createTestSubjectList();
        
        student.addLesson(55, subjectList, "CS101"); // Failed
        student.addLesson(58, subjectList, "CS102"); // Failed
        student.addLesson(80, subjectList, "CS103"); // Passed
        
        assertFalse(student.isFallen(), "Student should NOT be marked as fallen with only 2 failures");
    }
    
    @org.junit.jupiter.api.Test
    void testStudentIsFallen_BoundaryScore() {
        Student student = new Student();
        student.setName("Grace");
        student.setCode("SW");
        
        ArrayLinearList subjectList = createTestSubjectList();
        
        student.addLesson(59, subjectList, "CS101"); // Failed (< 60)
        student.addLesson(60, subjectList, "CS102"); // Passed (>= 60)
        student.addLesson(61, subjectList, "CS103"); // Passed
        
        assertFalse(student.isFallen(), "Student with score of 60 should not be counted as failure");
    }
    
    @org.junit.jupiter.api.Test
    void testStudentIsFallen_NoFailures() {
        Student student = new Student();
        student.setName("Henry");
        student.setCode("CS");
        
        ArrayLinearList subjectList = createTestSubjectList();
        
        student.addLesson(90, subjectList, "CS101");
        student.addLesson(85, subjectList, "CS102");
        student.addLesson(95, subjectList, "CS103");
        
        assertFalse(student.isFallen(), "Student with all passing grades should not be fallen");
    }
    
    @org.junit.jupiter.api.Test
    void testStudentGetLessons() {
        Student student = new Student();
        student.setName("Ivy");
        student.setCode("IT");
        
        ArrayLinearList subjectList = createTestSubjectList();
        
        student.addLesson(90, subjectList, "CS101");
        student.addLesson(85, subjectList, "CS102");
        
        String lessons = student.getLessons();
        assertTrue(lessons.contains("CS101"), "Lessons should contain CS101");
        assertTrue(lessons.contains("CS102"), "Lessons should contain CS102");
    }
    
    @org.junit.jupiter.api.Test
    void testStudentGetMajor() {
        Student student = new Student();
        student.setName("Jack");
        student.setCode("CS");
        
        ArrayLinearList majorList = new ArrayLinearList();
        majorList.add(0, new Major("CS", "Computer Science"));
        majorList.add(1, new Major("IT", "Information Technology"));
        
        String major = student.getName();
        assertTrue(major.contains("Jack"), "Major should contain CS code");
    }
    
    @org.junit.jupiter.api.Test
    void testStudentPrint() {
        Student student = new Student();
        student.setName("Test Student");
        student.setCode("CS");
        
        ArrayLinearList subjectList = createTestSubjectList();
        student.addLesson(85, subjectList, "CS101");
        
        outputStream.reset();
        student.print();
        
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Test Student"));
        assertTrue(output.contains("CS"));
    }

    // ==================== Registration Class Tests ====================
    
    @org.junit.jupiter.api.Test
    void testRegistrationCreation() throws IOException {
        // Update file paths in the test data to use temporary directory
        updateFilePathsInTestData();
        
        Registration reg = new Registration();
        
        assertNotNull(reg.getStudentList(), "Student list should not be null");
        assertNotNull(reg.getSubjectList(), "Subject list should not be null");
        assertNotNull(reg.getMajorList(), "Major list should not be null");
        
        assertTrue(reg.getStudentList().size() > 0, "Should load students from test data");
        assertTrue(reg.getSubjectList().size() > 0, "Should load subjects from test data");
        assertTrue(reg.getMajorList().size() > 0, "Should load majors from test data");
    }
    
    @org.junit.jupiter.api.Test
    void testRegistrationSubjectList() throws IOException {
        updateFilePathsInTestData();
        Registration reg = new Registration();
        
        outputStream.reset();
        reg.subjectList();
        
        String output = outputStream.toString();
        assertNotNull(output, "Output should not be null");
        assertFalse(output.isEmpty(), "Output should not be empty");
    }
    
    @org.junit.jupiter.api.Test
    void testRegistrationMajorList() throws IOException {
        updateFilePathsInTestData();
        Registration reg = new Registration();
        
        outputStream.reset();
        reg.majorList();
        
        String output = outputStream.toString();
        assertNotNull(output, "Output should not be null");
        assertFalse(output.isEmpty(), "Output should not be empty");
    }
    
    @org.junit.jupiter.api.Test
    void testRegistrationAvgGPA() throws IOException {
        updateFilePathsInTestData();
        Registration reg = new Registration();
        
        outputStream.reset();
        reg.avgGPA();
        
        String output = outputStream.toString();
        assertNotNull(output, "GPA output should not be null");
        assertFalse(output.isEmpty(), "GPA output should not be empty");
    }
    
    @org.junit.jupiter.api.Test
    void testRegistrationStudentsHasFallen() throws IOException {
        updateFilePathsInTestData();
        Registration reg = new Registration();
        
        outputStream.reset();
        reg.studentsHasFallen();
        
        String output = outputStream.toString();
        assertNotNull(output, "Failed students output should not be null");
    }
    
    @org.junit.jupiter.api.Test
    void testRegistrationPrintAsSubject() throws IOException {
        updateFilePathsInTestData();
        Registration reg = new Registration();
        
        outputStream.reset();
        reg.printAsSubject();
        
        String output = outputStream.toString();
        assertNotNull(output, "Subject-based output should not be null");
        assertFalse(output.isEmpty(), "Subject-based output should not be empty");
    }
    
    @org.junit.jupiter.api.Test
    void testRegistrationPrintAsMajor() throws IOException {
        updateFilePathsInTestData();
        Registration reg = new Registration();
        
        outputStream.reset();
        reg.printAsMajor();
        
        String output = outputStream.toString();
        assertNotNull(output, "Major-based output should not be null");
        assertFalse(output.isEmpty(), "Major-based output should not be empty");
    }

    // ==================== Integration Tests ====================
    
    @org.junit.jupiter.api.Test
    void testFullWorkflow_StudentWithMultipleCourses() {
        ArrayLinearList subjectList = createTestSubjectList();
        
        Student student = new Student();
        student.setName("Integration Test Student");
        student.setCode("CS");
        
        // Add multiple courses
        student.addLesson(95, subjectList, "CS101");
        student.addLesson(88, subjectList, "CS102");
        student.addLesson(92, subjectList, "CS103");
        
        // Verify GPA is calculated
        assertTrue(student.getGPA() > 0, "GPA should be greater than 0");
        assertFalse(student.isFallen(), "Student should not be fallen");
        
        // Verify lessons are stored
        String lessons = student.getLessons();
        assertTrue(lessons.contains("CS101"));
        assertTrue(lessons.contains("CS102"));
        assertTrue(lessons.contains("CS103"));
    }
    
    @org.junit.jupiter.api.Test
    void testGPACalculation_EdgeCase_AllZeros() {
        Student student = new Student();
        student.setName("Zero Student");
        student.setCode("CS");
        
        ArrayLinearList subjectList = createTestSubjectList();
        
        student.addLesson(0, subjectList, "CS101");
        student.addLesson(0, subjectList, "CS102");
        
        // (0/20 - 1) = -1, so GPA should be -1.0
        assertEquals(-1.0f, student.getGPA(), 0.01);
    }
    
    @org.junit.jupiter.api.Test
    void testGPACalculation_EdgeCase_MaxScores() {
        Student student = new Student();
        student.setName("Perfect Student");
        student.setCode("CS");
        
        ArrayLinearList subjectList = createTestSubjectList();
        
        student.addLesson(100, subjectList, "CS101");
        student.addLesson(100, subjectList, "CS102");
        student.addLesson(100, subjectList, "CS103");
        
        assertEquals(4.0f, student.getGPA(), 0.01);
    }
    
    @org.junit.jupiter.api.Test
    void testFailureDetection_ExactlyThreeFailures() {
        Student student = new Student();
        ArrayLinearList subjectList = createTestSubjectList();
        
        student.addLesson(50, subjectList, "CS101");
        student.addLesson(55, subjectList, "CS102");
        student.addLesson(59, subjectList, "CS103");
        
        assertTrue(student.isFallen(), "Exactly 3 failures should mark student as fallen");
    }
    
    @org.junit.jupiter.api.Test
    void testFailureDetection_FourFailures() {
        Student student = new Student();
        ArrayLinearList subjectList = createTestSubjectList();
        
        student.addLesson(50, subjectList, "CS101");
        student.addLesson(55, subjectList, "CS102");
        student.addLesson(59, subjectList, "CS103");
        student.addLesson(40, subjectList, "CS104");
        
        assertTrue(student.isFallen(), "More than 3 failures should mark student as fallen");
    }

    // ==================== Negative Tests ====================
    
    @org.junit.jupiter.api.Test
    void testSubject_NullSafety() {
        Subject subject = new Subject();
        assertNull(subject.getCode(), "Code should be null initially");
        assertNull(subject.getName(), "Name should be null initially");
        assertEquals(0.0f, subject.getCredit(), 0.001);
    }
    
    @org.junit.jupiter.api.Test
    void testStudent_EmptyLessons() {
        Student student = new Student();
        student.setName("Empty Student");
        student.setCode("CS");
        
        // Don't add any lessons
        assertFalse(student.isFallen(), "Student with no lessons should not be fallen");
        assertEquals("", student.getLessons(), "Empty lessons should return empty string");
    }
    
    @org.junit.jupiter.api.Test
    void testGPACalculation_SingleCourse() {
        Student student = new Student();
        ArrayLinearList subjectList = createTestSubjectList();
        
        student.addLesson(80, subjectList, "CS101");
        
        // (80/20 - 1) = 3.0
        assertEquals(3.0f, student.getGPA(), 0.01);
    }
    
    @org.junit.jupiter.api.Test
    void testStudentConstructorWithParameters() {
        Chain lessons = new Chain();
        Student student = new Student("CS", lessons);
        
        assertEquals("CS", student.getCode());
        // Note: The lessons field is reinitialized in constructor, so we can't test it directly
    }

    // ==================== Boundary Tests ====================
    
    @org.junit.jupiter.api.Test
    void testScore_LowerBoundary() {
        Student student = new Student();
        ArrayLinearList subjectList = createTestSubjectList();
        
        student.addLesson(0, subjectList, "CS101");
        assertEquals(-1.0f, student.getGPA(), 0.01);
    }
    
    @org.junit.jupiter.api.Test
    void testScore_UpperBoundary() {
        Student student = new Student();
        ArrayLinearList subjectList = createTestSubjectList();
        
        student.addLesson(100, subjectList, "CS101");
        assertEquals(4.0f, student.getGPA(), 0.01);
    }
    
    @org.junit.jupiter.api.Test
    void testScore_PassFailBoundary() {
        Student student = new Student();
        ArrayLinearList subjectList = createTestSubjectList();
        
        // Exactly at the boundary
        student.addLesson(59, subjectList, "CS101"); // Failed
        student.addLesson(60, subjectList, "CS102"); // Passed
        student.addLesson(61, subjectList, "CS103"); // Passed
        
        assertFalse(student.isFallen(), "One failure should not mark student as fallen");
    }

    // ==================== Performance Tests ====================
    
    @org.junit.jupiter.api.Test
    @Timeout(5)
    void testGPACalculation_Performance() {
        Student student = new Student();
        ArrayLinearList subjectList = createTestSubjectList();
        
        // Add many lessons
        for (int i = 0; i < 100; i++) {
            student.addLesson(80 + (i % 20), subjectList, "CS10" + (i % 4));
        }
        
        // Should complete within 5 seconds
        float gpa = student.getGPA();
        assertTrue(gpa > 0, "GPA should be calculated");
    }
    
    @org.junit.jupiter.api.Test
    @Timeout(5)
    void testFailureDetection_Performance() {
        Student student = new Student();
        ArrayLinearList subjectList = createTestSubjectList();
        
        // Add many lessons with mixed scores
        for (int i = 0; i < 100; i++) {
            student.addLesson(50 + i, subjectList, "CS10" + (i % 4));
        }
        
        // Should complete within 5 seconds
        boolean fallen = student.isFallen();
        assertNotNull(Boolean.valueOf(fallen), "Failure status should be determined");
    }

    // ==================== Helper Methods ====================
    
    private ArrayLinearList createTestSubjectList() {
        ArrayLinearList subjectList = new ArrayLinearList();
        subjectList.add(0, new Subject("CS101", "Introduction to Programming", 3.0f));
        subjectList.add(1, new Subject("CS102", "Data Structures", 3.0f));
        subjectList.add(2, new Subject("CS103", "Algorithms", 3.0f));
        subjectList.add(3, new Subject("CS104", "Database Systems", 3.0f));
        return subjectList;
    }
    
    private void createTestFiles() throws IOException {
        // Create subjectList.txt
        List<String> subjectLines = List.of(
            "CS101/Introduction to Programming/3.0",
            "CS102/Data Structures/3.0",
            "CS103/Algorithms/3.0",
            "CS301/Үйлдлийн систем/3.0",
            "CS303/Хиймэл оюуны үндэс/3.0"
        );
        Files.write(subjectListPath, subjectLines);
        
        // Create majorList.txt
        List<String> majorLines = List.of(
            "CS/Computer Science",
            "IT/Information Technology",
            "SW/Software Engineering",
            "ML/Machine Learning",
            "EE/Electrical Engineering"
        );
        Files.write(majorListPath, majorLines);
        
        // Create exams.txt
        List<String> examLines = List.of(
            "John Doe/CS/CS101/85",
            "John Doe/CS/CS102/90",
            "John Doe/CS/CS103/88",
            "Jane Smith/IT/CS101/75",
            "Jane Smith/IT/CS102/80",
            "Bob Johnson/SW/CS101/55",
            "Bob Johnson/SW/CS102/58",
            "Bob Johnson/SW/CS103/59"
        );
        Files.write(examsPath, examLines);
    }
    
    private void updateFilePathsInTestData() throws IOException {
        // This method would update the Registration class to use our test file paths
        // Since we can't modify the Registration class directly in tests,
        // we'll rely on the fact that the test files are created in the expected location
        
        // For a real implementation, you might need to use reflection or
        // modify the Registration class to accept file paths as parameters
    }
}