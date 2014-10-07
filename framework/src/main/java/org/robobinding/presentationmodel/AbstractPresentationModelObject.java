package org.robobinding.presentationmodel;

import java.util.Map;
import java.util.Set;

import org.robobinding.function.FunctionSupply;
import org.robobinding.function.MethodDescriptor;
import org.robobinding.property.ObservableBean;
import org.robobinding.property.PropertyChangeListener;
import org.robobinding.property.PropertyDescriptor;
import org.robobinding.property.PropertySupply;

/**
 * @since 1.0
 * @author Cheng Wei
 *
 */
public abstract class AbstractPresentationModelObject implements PropertySupply, FunctionSupply, ObservableBean {
	private final Class<?> presentationModelClass;
	protected final PresentationModelChangeSupport changeSupport;

	protected AbstractPresentationModelObject(Class<?> presentationModelClass, PresentationModelChangeSupport changeSupport) {
		this.presentationModelClass = presentationModelClass;
		this.changeSupport = changeSupport;
	}

	public abstract Set<String> propertyNames();

	public abstract Set<String> dataSetPropertyNames();
	/*
	protected Set<String> allPropertyNames() {
		Set<String> allPropertyNames = Sets.newHashSet(propertyNames());
		allPropertyNames.addAll(dataSetPropertyNames());
		return allPropertyNames;
	}*/

	public abstract Map<String, Set<String>> propertyDependencies();

	public abstract Set<MethodDescriptor> eventMethods();
	
	
	protected MethodDescriptor createMethodDescriptor(String name) {
		return createMethodDescriptor(name, new Class<?>[0]);
	}
	
	protected MethodDescriptor createMethodDescriptor(String name, Class<?>... parameterTypes) {
		return new MethodDescriptor(name, parameterTypes);
	}
	
	protected PropertyDescriptor createPropertyDescriptor(Class<?> propertyType, String propertyName, boolean readable, boolean writable) {
		return new PropertyDescriptor(presentationModelClass, propertyType, propertyName, readable, writable);
	}
	
	protected PropertyDescriptor createDataSetPropertyDescriptor(Class<?> propertyType, String propertyName) {
		return new PropertyDescriptor(presentationModelClass, propertyType, propertyName, true, false);
	}
	
	@Override
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(propertyName, listener);
	}
	
	@Override
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(propertyName, listener);
	}

	public Class<?> getPresentationModelClass() {
		return presentationModelClass;
	}
}