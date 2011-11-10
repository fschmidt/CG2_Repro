package de.bht.fb6.cg1.mesh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.bht.fb6.cg1.math.IColumnVector;

/**
 * This class represents a triangle mesh with an array of vertices, an array
 * of attributes and an array containing indicies for the mesh.
 *
 * @author Stephan Rehfeld
 */
public class TriangleMesh< T extends Number > {

    /**
     * The array containing the vertices of the mesh.
     */
    private final List< IColumnVector< T > > vertices;

    /**
     * The array containing the attributes for each vertex. Index is the same as in vertices array.
     */
    private final List< Map< String, Object > > attributes;

    /**
     * The array containing the indicies for the mesh.
     */
    private final List< Integer > indicies;


    /**
     * This constructor creates a new triangle mesh.
     *
     * @param vertices The vertices of the mesh. Must not be null. Must be at least 3 vertices.
     * @param attributes The attributes for the verticies. Size must equal the size of the indicies. Must not be null.
     * @param indicies The indicies. Must be divideable by 3. Must not be null.
     */
    public TriangleMesh( final List< IColumnVector< T > > vertices, final List< Map< String, Object > > attributes, final List< Integer > indicies ) {
        if( vertices == null ) throw new IllegalArgumentException( "The parameter 'vertices' must not be 'null'!" );
        if( vertices.size() < 3 ) throw new IllegalArgumentException( "You must pass at least 3 vertices!" );
        if( attributes == null ) throw new IllegalArgumentException( "The parameter 'attributes' must not be 'null'!" );
        if( attributes.size() < 3 ) throw new IllegalArgumentException( "You must pass at least 3 maps!" );
        if( indicies.size() % 3 != 0 ) throw new IllegalArgumentException( "The indicies array must be divideable by 3!" );
        this.vertices = new ArrayList< IColumnVector< T > >();

        for( int i = 0; i < vertices.size(); ++i ) {
            if( vertices.get( i ) == null ) throw new IllegalArgumentException( "None of the given vertices must be null!" );
            this.vertices.add( vertices.get( i ) );
        }

        final Set< String > keySet = attributes.get( 0 ).keySet();
        this.attributes = new ArrayList< Map< String, Object > >();
        for( int i = 0; i < attributes.size(); ++i ) {
            final Map<String, Object> newSet = attributes.get( i );
			//HÄ?
			//if( !e ) throw new IllegalArgumentException( "All attributes must have the same keys!" );
            final Map< String, Object > a = new HashMap< String, Object >();
            for( final String key : keySet )
                a.put( key, newSet.get( key ) );
            this.attributes.add( a );
        }

        this.indicies = new ArrayList< Integer >();
        for( final int i : indicies ) this.indicies.add( i );
    }

    /**
     * This method returns a copy of the attributes array.
     *
     * @return A copy of the attributes array. Never returns null.
     */
    public List< Map< String, Object > > getAttributes() {
        final List< Map< String, Object > > a = new ArrayList< Map< String, Object > >();
        for( final Map< String, Object > e : this.attributes ) a.add( new HashMap< String, Object >( e ) );
        return a;
    }

    /**
     * This method returns a copy the indicies array.
     *
     * @return A copy of the indicies array. Never returns null.
     */
    public List< Integer > getIndicies() {
        return new ArrayList< Integer >( this.indicies );
    }

    /**
     * This method returns a copy of the vertices array.
     *
     * @return A copy of the vertices array. Never returns null.
     */
    public List< IColumnVector< T > > getVertices() {
        return new ArrayList< IColumnVector< T > >( this.vertices );
    }

    /**
     * This method returns a vertex from the array.
     *
     * @param i The index of the vertex.
     * @return Returns the vertex.
     */
    public IColumnVector< T > getVertex( final int i ) {
        return this.vertices.get( i );
    }

    /**
     * This method return a map containing the attributes for a vertex.
     *
     * @param i Index of the vertex.
     * @return The attributes of the vertex.
     */
    public Map< String, Object > getAttributesOfVertex( final int i ) {
        return new HashMap< String, Object >( this.attributes.get( i ) );
    }

    /**
     * This method converts the triangle mesh to single triangles.
     *
     * @return A list containing the mesh as single triangles.
     */
    public List< Triangle< T > > asTriangles() {
		final ArrayList<Triangle<T>> set = new ArrayList<Triangle<T>>();

		assert (this.indicies.size() % 3 == 0);

		for (int i = 0; i != indicies.size(); i += 3) {
			final int a = indicies.get(i);
			final int b = indicies.get(i + 1);
			final int c = indicies.get(i + 2);

			@SuppressWarnings("unchecked")
			final Triangle<T> t = new Triangle<T>(vertices.get(a), vertices.get(b), vertices.get(c));

			set.add(t);
		}

		return set;
    }
    
}
