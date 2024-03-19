package SmartHome.ddd;

import java.util.Optional;

public abstract interface Repository<ID extends DomainId, T extends AggregateRoot<ID> > {
  
  public T save(T entity);
  
  public Iterable<T> findAll();
  
  public Optional<T> getByIdentity(ID id);
  
  public boolean containsIdentity(ID id);
}