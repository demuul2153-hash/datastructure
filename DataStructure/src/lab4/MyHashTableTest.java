package lab4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MyHashTableTest {

    private MyHashTable hashTable;

    @BeforeEach
    void setUp() {
        hashTable = new MyHashTable(11); // Using a prime number as divisor
    }

    @Test
    void testInsertAndGet() {
        // Test basic insertion and retrieval
        hashTable.put("key1", "value1");
        hashTable.put("key2", "value2");
        
        assertEquals("value1", hashTable.get("key1"));
        assertEquals("value2", hashTable.get("key2"));
        assertNull(hashTable.get("nonexistent"));
    }

    @Test
    void testUpdateElement() {
        // Test updating existing element
        hashTable.put("key1", "oldValue");
        Object result = hashTable.updateElement("key1", "newValue");
        
        assertEquals("oldValue", result);
        assertEquals("newValue", hashTable.get("key1"));
    }

    @Test
    void testUpdateElementNonExistentKey() {
        // Test updating non-existent key
        Object result = hashTable.updateElement("nonexistent", "value");
        assertNull(result);
    }

    @Test
    void testUpdateKey() {
        // Test updating key
        hashTable.put("oldKey", "value");
        Object result = hashTable.updateKey("oldKey", "newKey");
        
        assertEquals("value", result);
        assertNull(hashTable.get("oldKey"));
        assertEquals("value", hashTable.get("newKey"));
    }

    @Test
    void testUpdateKeyToExistingKey() {
        // Test updating key to an already existing key
        hashTable.put("key1", "value1");
        hashTable.put("key2", "value2");
        
        Object result = hashTable.updateKey("key1", "key2");
        assertEquals("value1", result);
        // key2 should still have its original value
        assertEquals("value2", hashTable.get("key2"));
    }

    @Test
    void testUpdateKeyNonExistent() {
        // Test updating non-existent key
        Object result = hashTable.updateKey("nonexistent", "newKey");
        assertNull(result);
    }

    @Test
    void testDelete() {
        // Test deletion
        hashTable.put("key1", "value1");
        hashTable.put("key2", "value2");
        
        hashTable.delete("key1");
        
        assertNull(hashTable.get("key1"));
        assertEquals("value2", hashTable.get("key2"));
    }

    @Test
    void testDeleteNonExistent() {
        // Test deleting non-existent key
        // This should not throw an exception
        hashTable.delete("nonexistent");
    }

    @Test
    void testCollisionHandling() {
        // Test handling of hash collisions
        // Using a small divisor to force collisions
        MyHashTable smallTable = new MyHashTable(3);
        
        smallTable.put("a", "valueA");
        smallTable.put("d", "valueD"); // Potential collision
        
        assertEquals("valueA", smallTable.get("a"));
        assertEquals("valueD", smallTable.get("d"));
    }

    @Test
    void testMultipleOperations() {
        // Test sequence of multiple operations
        hashTable.put("key1", "value1");
        hashTable.put("key2", "value2");
        
        // Update element
        hashTable.updateElement("key1", "updatedValue1");
        
        // Update key
        hashTable.updateKey("key2", "newKey2");
        
        // Delete
        hashTable.delete("key1");
        
        assertNull(hashTable.get("key1"));
        assertNull(hashTable.get("key2"));
        assertEquals("updatedValue1", hashTable.get("newKey2"));
    }

    @Test
    void testNullHandling() {
        // Test handling of null values
        hashTable.put("key1", null);
        assertNull(hashTable.get("key1"));
        
        // Test updating with null element
        hashTable.put("key2", "value2");
        Object result = hashTable.updateElement("key2", null);
        assertEquals("value2", result);
        assertNull(hashTable.get("key2"));
    }

    @Test
    void testEmptyTableOperations() {
        // Test operations on empty table
        assertNull(hashTable.get("anykey"));
        assertNull(hashTable.updateElement("anykey", "value"));
        assertNull(hashTable.updateKey("anykey", "newkey"));
        
        // Delete should not throw exception
        hashTable.delete("anykey");
    }

    @Test
    void testSizeAfterOperations() {
        // You might want to add a getSize() method to your class to test this properly
        // For now, we'll test through the public interface
        
        hashTable.put("key1", "value1");
        hashTable.put("key2", "value2");
        
        // After two insertions, both should be retrievable
        assertEquals("value1", hashTable.get("key1"));
        assertEquals("value2", hashTable.get("key2"));
        
        hashTable.delete("key1");
        assertNull(hashTable.get("key1"));
        assertEquals("value2", hashTable.get("key2"));
    }
}