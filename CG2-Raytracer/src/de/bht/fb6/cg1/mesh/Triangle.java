package de.bht.fb6.cg1.mesh;

import de.bht.fb6.cg1.math.IColumnVector;
import java.util.List;
import java.util.Map;

/**
 * This class represents a triangle.
 *
 * @author Stephan Rehfeld
 */
public class Triangle< T extends Number > extends Polygon< T > {

    /**
     * This constructor creates a new triangle.
     *
     * @param vertices The vertices. Must be exacly 3. Must not be null!
     */
    public Triangle( final IColumnVector< T >... vertices ) {
        super( vertices );
        if( this.size() > 3 ) throw new IllegalArgumentException( "You must pass 3 vertices!" );
    }

    /**
     * This constructor creates a new triangle.
     *
     * @param vertices The vertices. Must not be null!
     * @param attributes The attributes. Must not be null!
     */
    public Triangle( final List< IColumnVector< T > > vertices, final List< Map< String, Object > > attributes ) {
        super( vertices, attributes );
        if( this.size() > 3 ) throw new IllegalArgumentException( "You must pass 3 vertices!" );
    }

    /**
     * This method returns the first vertex.
     *
     * @return The first vertex.
     */
    public IColumnVector< T > getFirst() {
        return this.get( 0 );
    }

    /**
     * This method returns the second vertex.
     *
     * @return The second vertex.
     */
    public IColumnVector< T > getSecond() {
        return this.get( 1 );
    }

    /**
     * This method returns the third vertex.
     *
     * @return The thirs vertex.
     */
    public IColumnVector< T > getThird() {
        return this.get( 2 );
    }

    /**
     * This method returns the value of an attribute of the first vertex.
     *
     * @param name The name of the attribute.
     * @return The value.
     */
    public Object getAttributeOfFirst( final String name ) {
        return this.getAttributesValueOfVertex( 0, name );
    }

    /**
     * This method returns the value of an attribute of the second vertex.
     *
     * @param name The name of the attribute.
     * @return The value.
     */
    public Object getAttributeOfSecond( final String name ) {
        return this.getAttributesValueOfVertex( 1, name );
    }

    /**
     * This method returns the value of an attribute of the third vertex.
     *
     * @param name The name of the attribute.
     * @return The value.
     */
    public Object getAttributeOfThird( final String name ) {
        return this.getAttributesValueOfVertex( 2, name );
    }

}
