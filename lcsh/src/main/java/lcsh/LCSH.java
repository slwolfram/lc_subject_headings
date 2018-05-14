package lcsh;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;

/* The LCSH object class. Records information about the (its name, uri, and number of catalog items which 
 * contain the heading) and links to its adjacent headings. 
 */

public class LCSH implements Serializable{

	private String name; // the authoritative name or title
	private String uri; // the heading's uri
	private ArrayList<LCSH> narrowerHeadings = new ArrayList<LCSH>(); // more specific subject topics
	private ArrayList<LCSH> broaderHeadings = new ArrayList<LCSH>();; // more general topics
	private ArrayList<LCSH> relatedHeadings = new ArrayList<LCSH>();; // related at the same level of generality. Much less numerous than
												// narrower/broader headings.

	private ArrayList<LCSH> subdivisions = new ArrayList<LCSH>();; // subdivisions (ex. cities and towns--united states is a subdivided term under cities and towns) 
									//are not taken directly from lc linked data, but my knowledge of 
									//cataloging suggested that it might be useful information to have. 
								// Traversing a heading from its subdivisions is similar to using the narrower
								// heading field - I was surprised that subdivisions were not encoded
								// as narrower headings more generally.
								// Using subdivisions may be useful for incorporating more terms in a hierarchy, 
								// but I have noticed that if subdivisions are treated like "narrower headings" 
								// you can get infinite loops when building a hierarchy.

	private ArrayList<String> variantTerms = new ArrayList<String>();; // synonyms for the heading name. Important for keyword search.
	private int count; // number of catalog items that contain this heading

	public LCSH(String name, String uri, int count) {
		setName(name);
		setUri(uri);
		setCount(count);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public ArrayList<LCSH> getNarrowerHeadings() {
		return narrowerHeadings;
	}
	
	public void addNarrowerHeading(LCSH narrowerHeading) {
		this.narrowerHeadings.add(narrowerHeading);
	}
	
	public void setNarrowerHeadings(ArrayList<LCSH> narrowerHeadings) {
		this.narrowerHeadings = narrowerHeadings;
	}

	public ArrayList<LCSH> getBroaderHeadings() {
		return broaderHeadings;
	}

	public void addBroaderHeading(LCSH broaderHeading) {
		this.broaderHeadings.add(broaderHeading);
	}

	public void setBroaderHeadings(ArrayList<LCSH> broaderHeadings) {
		this.broaderHeadings = broaderHeadings;
	}

	public void addRelatedHeading(LCSH relatedHeading) {
		this.relatedHeadings.add(relatedHeading);
	}
	
	public ArrayList<LCSH> getRelatedHeadings() {
		return relatedHeadings;
	}

	public void setRelatedHeadings(ArrayList<LCSH> relatedHeadings) {
		this.relatedHeadings = relatedHeadings;
	}

	public void addSubdivision(LCSH subdivision) {
		this.subdivisions.add(subdivision);
	}
	
	public ArrayList<LCSH> getSubdivisions() {
		return subdivisions;
	}

	public void setSubdivisions(ArrayList<LCSH> subdivisions) {
		this.subdivisions = subdivisions;
	}

	public ArrayList<String> getVariantTerms() {
		return variantTerms;
	}

	public void addVariantTerm(String variantTerm) {
		this.variantTerms.add(variantTerm);
	}
	
	public void setVariantTerms(ArrayList<String> variantTerms) {
		this.variantTerms = variantTerms;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
