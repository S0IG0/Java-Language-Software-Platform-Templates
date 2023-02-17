package com.company.practics.practic_3;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class SemaphoreSet<T> implements Set<T> {

    private final Set<T> set;
    private final Semaphore semaphore;

    public SemaphoreSet() {
        this.set = new HashSet<>();
        this.semaphore = new Semaphore(1);
    }

    public SemaphoreSet(int capacity) {
        this.set = new HashSet<>(capacity);
        this.semaphore = new Semaphore(1);
    }

    @Override
    public int size() {
        try {
            semaphore.acquire();
            return set.size();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("SemaphoreSet interrupted while acquiring semaphore");
        } finally {
                semaphore.release();
        }
    }

    @Override
    public boolean isEmpty() {
        try {
            semaphore.acquire();
            return set.isEmpty();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("SemaphoreSet interrupted while acquiring semaphore");
        } finally {
            semaphore.release();
        }
    }

    @Override
    public boolean contains(Object o) {
        try {
            semaphore.acquire();
            return set.contains(o);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("SemaphoreSet interrupted while acquiring semaphore");
        } finally {
            semaphore.release();
        }
    }

    @Override
    public Iterator<T> iterator() {
        try {
            semaphore.acquire();
            return new HashSet<>(set).iterator();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("SemaphoreSet interrupted while acquiring semaphore");
        } finally {
            semaphore.release();
        }
    }

    @Override
    public Object[] toArray() {
        try {
            semaphore.acquire();
            return set.toArray();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("SemaphoreSet interrupted while acquiring semaphore");
        } finally {
            semaphore.release();
        }
    }

    @Override
    public <T> T[] toArray(T[] a) {
        try {
            semaphore.acquire();
            return set.toArray(a);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("SemaphoreSet interrupted while acquiring semaphore");
        } finally {
            semaphore.release();
        }
    }

    @Override
    public boolean add(T t) {
        try {
            semaphore.acquire();
            return set.add(t);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("SemaphoreSet interrupted while acquiring semaphore");
        } finally {
            semaphore.release();
        }
    }

    @Override
    public boolean remove(Object o) {
        try {
            semaphore.acquire();
            return set.remove(o);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("SemaphoreSet interrupted while acquiring semaphore");
        } finally {
            semaphore.release();
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        try {
            semaphore.acquire();
            return set.containsAll(c);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("SemaphoreSet interrupted while acquiring semaphore");
        } finally {
            semaphore.release();
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        try {
            semaphore.acquire();
            return set.addAll(c);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("SemaphoreSet interrupted while acquiring semaphore");
        } finally {
            semaphore.release();
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        try {
            semaphore.acquire();
            return set.retainAll(c);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("SemaphoreSet interrupted while acquiring semaphore");
        } finally {
            semaphore.release();
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        try {
            semaphore.acquire();
            return set.removeAll(c);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("SemaphoreSet interrupted while acquiring semaphore");
        } finally {
            semaphore.release();
        }
    }

    @Override
    public void clear() {
        try {
            semaphore.acquire();
            set.clear();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("SemaphoreSet interrupted while acquiring semaphore");
        } finally {
            semaphore.release();
        }
    }

    @Override
    public String toString() {
        return "SemaphoreSet{" +
                "set=" + set +
                ", semaphore=" + semaphore +
                '}';
    }
}
