package de.bht.fb6.cg1.mesh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.bht.fb6.cg1.math.IColumnVector;

/**
 * This class represents a polygon.
 *
 * @author Stephan Rehfeld
 */
public class Polygon< T extends Number > {

    /**
     * The vertices
     */
    private final List< IColumnVector< T > > vertices;

    /**
     * The atttributes of the vertices.
     */
    private final List< Map< String, Object > > attributes;

    /**
     * This constructor creates a new polygon.
     *
     * @param vertices The vertices of the polygon.
     */
    public Polygon( final IColumnVector< T >... vertices ) {
        if( vertices == null ) throw new IllegalArgumentException( "The parameter 'vertices' must not be 'null'!" );
        if( vertices.length < 3 ) throw new IllegalArgumentException( "You must pass at least 3 vertices!" );
        this.vertices = new ArrayList< IColumnVector< T > >();
        for( int i = 0; i < vertices.length; ++i ) {
            if( vertices[i] == null ) throw new IllegalArgumentException( "None of the given vertices must be null!" );
            this.vertices.add( vertices[i] );
        }
        this.attributes = new ArrayList< Map< String, Object > >();
        final Map< String, Object > m = new HashMap< String, Object >();
        for( int i = 0; i < this.vertices.size(); ++i ) this.attributes.add( m );
    }

    /**
     * This constructor creates a new polygon.
     *
     * @param vertices The vertices.
     * @param attributes The attributes.
     */
    @SuppressWarnings("unchecked")
	public Polygon( final List< IColumnVector< T > > vertices, final List< Map< String, Object > > attributes ) {
        this( vertices.toArray( new IColumnVector[0] ) );
        if( attributes == null ) throw new IllegalArgumentException( "The parameter 'attributes' must not be 'null'!" );
        if( attributes.size() < 3 ) throw new IllegalArgumentException( "You must pass at least 3 maps!" );
        if( attributes.size() != vertices.size() ) throw new IllegalArgumentException( "Amount of attributes must match the amount of vertices!" );
        final Set< String > keySet = attributes.get( 0 ).keySet();
        for( int i = 0; i < attributes.size(); ++i ) {
            if( !attributes.get( i ).equals( keySet ) ) throw new IllegalArgumentException( "All attributes must have the same keys!" );
            final Map< String, Object > a = new HashMap< String, Object >();
            for( final String key : keySet )
                a.put( key, attributes.get( i ).get( key ) );
            this.attributes.add( a );
        }

    }

    /**
     * This method returns the amount of vertices.
     *
     * @return The amount of vertices.
     */
    public int size() {
        return vertices.size();
    }

    /**
     * This method returns the requested vertex.
     *
     * @param i The index of the vertex.
     * @return The vertex.
     */
    public IColumnVector< T > get( final int i ) {
        return vertices.get(i);
    }

    /**
     * This method returns the names of the attributes.
     *
     * @return A set which contains the names of the attributes.
     */
    public Set< String > getAttributeNames() {
        return this.attributes.get( 0 ).keySet();
    }

    /**
     * This method returns the value of the attribute of a vertex.
     *
     * @param vertex The index of the vertex.
     * @param name The name of the attribute.
     * @return The value of the attribute.
     */
    public Object getAttributesValueOfVertex( final int vertex, final String name ) {
        return this.attributes.get( vertex ).get( name );
    }
}
