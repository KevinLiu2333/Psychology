package widgets.Bookmark
{

import mx.core.ClassFactory;

import spark.components.DataGroup;

// these events bubble up from the BookmarkItemRenderer
[Event(name="bookmarkClick", type="flash.events.Event")]
[Event(name="bookmarkDelete", type="flash.events.Event")]

public class BookmarkDataGroup extends DataGroup
{
    public function BookmarkDataGroup()
    {
        super();

        this.itemRenderer = new ClassFactory(BookmarkItemRenderer);
    }
}

}
