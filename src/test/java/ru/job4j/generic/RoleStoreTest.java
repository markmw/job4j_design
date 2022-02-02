package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {
    @Test
    public void whenAddAndFindThenRoleIsCleaner() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Cleaner"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Cleaner"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Cleaner"));
        Role result = store.findById("11");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleIsCleaner() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Cleaner"));
        store.add(new Role("1", "Driver"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Cleaner"));
    }

    @Test
    public void whenReplaceThenRoleIsDriver() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Cleaner"));
        store.replace("1", new Role("1", "Driver"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Driver"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRolename() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Cleaner"));
        store.replace("11", new Role("9", "Driver"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Cleaner"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Cleaner"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleameIsCleaner() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Cleaner"));
        store.delete("11");
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Cleaner"));
    }
}