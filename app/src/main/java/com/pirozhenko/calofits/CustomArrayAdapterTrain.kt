package com.pirozhenko.calofits

import android.R
import android.content.Context
import android.content.res.Resources.Theme
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import android.widget.ThemedSpinnerAdapter
import androidx.annotation.ArrayRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import java.util.ArrayList
import java.util.Arrays
import java.util.Collections
import java.util.Locale


//class CustomArrayAdapterTrain(context: Context, resource: Int, arrayList: ArrayList<ExerciseTrain?>?) :
//    ArrayAdapter<ExerciseTrain?>(context, resource, arrayList!!) {
//    (
//    private val context: Context,
//    private val textViewResourceId: Int,
//    private var myImageArray: ArrayList<ExerciseTrain>,
//    private var mFilter: ArrayList<ExerciseTrain>? = null,
//    val mLock: Any? = Any(),
//    var mOriginalValues: ArrayList<ExerciseTrain>? = null


//    private var mOriginalValues:
//) :
//    ArrayAdapter<ExerciseTrain>(context, textViewResourceId, myImageArray) {

//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        return createViewFromResource(position, convertView, parent)
//    }

//    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        return createViewFromResource(position, convertView, parent)
//    }
//
//    private fun createViewFromResource(
//        inflater: LayoutInflater, position: Int,
//        convertView: View?, parent: ViewGroup, resource: Int
//    ): View {
//        val view: View
//        val text: TextView?
//        view = convertView ?: inflater.inflate(resource, parent, false)
//        try {
//            if (mFieldId == 0) {
                //  If no custom field is assigned, assume the whole resource is a TextView
//                text = view as TextView
//            } else {
//                  Otherwise, find the TextView field within the layout
//                text = view.findViewById<TextView>(mFieldId)
//                if (text == null) {
//                    throw RuntimeException(
//                        "Failed to find view with ID "
//                                + mContext.getResources().getResourceName(mFieldId)
//                                + " in item layout"
//                    )
//                }
//            }
//        } catch (e: ClassCastException) {
//            Log.e("ArrayAdapter", "You must supply a resource ID for a TextView")
//            throw IllegalStateException(
//                "ArrayAdapter requires the resource ID to be a TextView", e
//            )
//        }
//        val item: T? = getItem(position)
//        if (item is CharSequence) {
//            text.text = item
//        } else {
//            text.setText(item.toString())
//        }
//        return view
//    }

//    private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup?): View{
//        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(textViewResourceId, parent, false) as TextView
//        view.text = myImageArray[position].name
//        return view
//    }
//    override fun getFilter(): Filter {
//        if (mFilter == null) {
//            mFilter = ArrayFilterTrain()
//        }
//        return mFilter
//    }

//    @Override
//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(prefix: CharSequence): FilterResults {
//                val results = FilterResults()
//                if (mOriginalValues == null) {
//                    synchronized(mLock!!) { mOriginalValues = java.util.ArrayList<ExerciseTrain>(myImageArray)
//                        Log.v("test", "1")
//                    }
//                }
//                if (prefix == null || prefix.length == 0) {
//                    val list: java.util.ArrayList<ExerciseTrain>
//                    synchronized(mLock!!) { list = java.util.ArrayList<ExerciseTrain>(mOriginalValues) }
//                    results.values = list
//                    results.count = list.size
//                } else {
//                    val prefixString = prefix.toString().lowercase(Locale.getDefault())
//                    val values: java.util.ArrayList<ExerciseTrain>
//                    synchronized(mLock!!) { values = java.util.ArrayList<ExerciseTrain>(mOriginalValues) }
//                    val count = values.size
//                    val newValues: java.util.ArrayList<ExerciseTrain> = java.util.ArrayList<ExerciseTrain>()
//                    for (i in 0 until count) {
//                        val value: String = values[i].name
//                        val valueText: String = value.toString().lowercase(Locale.getDefault())
//                        val valueTrain = values[i]
//
//                         First match against the whole, non-splitted value
//                        if (valueText.startsWith(prefixString)) {
//                            newValues.add(valueTrain)
//                        } else {
//                            val words = valueText.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
//                                .toTypedArray()
//                            for (word in words) {
//                                if (word.startsWith(prefixString)) {
//                                    newValues.add(valueTrain)
//                                    break
//                                }
//                            }
//                        }
//                    }
//                    results.values = newValues
//                    results.count = newValues.size
//                }
//                return results
//            }
//
//            override fun publishResults(constraint: CharSequence, results: FilterResults) {
//                myImageArray = results.values as ArrayList<ExerciseTrain>
//                Log.v("test", "2")
//
//                if (results.count > 0) {
//                    notifyDataSetChanged()
//                    Log.v("test", "3")
//
//                } else {
//                    notifyDataSetInvalidated()
//                }
//            }
//        }
//    }
//    @JvmInline
//    private class ArrayFilterTrain: Filter() {
//
//    }
//    private class ArrayFilter(): Filter
//    {
//        fun performFiltering(prefix: CharSequence?): FilterResults? {
//            val results = FilterResults()
//            if (mOriginalValues == null) {
//                synchronized(mLock) { mOriginalValues = java.util.ArrayList<T>(mObjects) }
//            }
//            if (prefix == null || prefix.length == 0) {
//                val list: java.util.ArrayList<T>
//                synchronized(mLock) { list = java.util.ArrayList<T>(mOriginalValues) }
//                results.values = list
//                results.count = list.size
//            } else {
//                val prefixString = prefix.toString().lowercase(Locale.getDefault())
//                val values: java.util.ArrayList<T>
//                synchronized(mLock) { values = java.util.ArrayList<T>(mOriginalValues) }
//                val count = values.size
//                val newValues: java.util.ArrayList<T> = java.util.ArrayList<T>()
//                for (i in 0 until count) {
//                    val value: T = values[i]
//                    val valueText: String = value.toString().lowercase(Locale.getDefault())
//
//                     First match against the whole, non-splitted value
//                    if (valueText.startsWith(prefixString)) {
//                        newValues.add(value)
//                    } else {
//                        val words = valueText.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
//                            .toTypedArray()
//                        for (word in words) {
//                            if (word.startsWith(prefixString)) {
//                                newValues.add(value)
//                                break
//                            }
//                        }
//                    }
//                }
//                results.values = newValues
//                results.count = newValues.size
//            }
//            return results
//        }


//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val inflater =
//            getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val view: View
//        view = inflater.inflate(R.layout.simple_list_item_1, null)
//        val textView = view.findViewById<View>(R.id.text1) as TextView
//        textView.text = myImageArray[position].name
//        textView.setOnClickListener {
//            Toast.makeText(
//                context,
//                "Clicked : " + myImageArray[position].name,
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//        return view
//    }
//}

//class HotelAdapter(context: Context, private val layoutResource: Int, private val hotels: List<ExerciseTrain>):
//    ArrayAdapter<ExerciseTrain>(context, layoutResource, hotels) {
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        return createViewFromResource(position, convertView, parent)
//    }
//
//    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        return createViewFromResource(position, convertView, parent)
//    }
//
//    private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup?): View{
//        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(layoutResource, parent, false) as TextView
//        view.text = hotels[position].name
//        return view
//    }
//
//    }
//class CustomArrayAdapter(context: Context,
//                         @LayoutRes private val layoutResource: Int,
//                         @IdRes private val textViewResourceId: Int = 0,
//                         private val values: List<ExerciseTrain>) : ArrayAdapter<ExerciseTrain>(context, layoutResource, values) {
//
//    override fun getItem(position: Int): ModelDisplayName = values[position]
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val view = createViewFromResource(convertView, parent, layoutResource)
//
//        return bindData(getItem(position), view)
//    }
//
//    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val view = createViewFromResource(convertView, parent, android.R.layout.simple_spinner_dropdown_item)
//
//        return bindData(getItem(position), view)
//    }
//
//    private fun createViewFromResource(convertView: View?, parent: ViewGroup, layoutResource: Int): TextView {
//        val context = parent.context
//        val view = convertView ?: LayoutInflater.from(context).inflate(layoutResource, parent, false)
//        return try {
//            if (textViewResourceId == 0) view as TextView
//            else {
//                view.findViewById(textViewResourceId) ?:
//                throw RuntimeException("Failed to find view with ID " +
//                        "${context.resources.getResourceName(textViewResourceId)} in item layout")
//            }
//        } catch (ex: ClassCastException){
//            Log.e("CustomArrayAdapter", "You must supply a resource ID for a TextView")
//            throw IllegalStateException(
//                "ArrayAdapter requires the resource ID to be a TextView", ex)
//        }
//    }
//
//    private fun bindData(value: ModelDisplayName, view: TextView): TextView {
//        view.text = value.displayName
//        return view
//    }
//}
//class NumbersViewAdapter  // invoke the suitable constructor of the ArrayAdapter class
//    (context: Context, resource: Int, arrayList: ArrayList<ExerciseTrain?>?) :
//    ArrayAdapter<ExerciseTrain?>(context, resource, arrayList!!) {
//    override fun getView(position: Int, @Nullable convertView: View, parent: ViewGroup): View {
//
//         convertView which is recyclable view
//        var currentItemView = convertView
//
//         of the recyclable view is null then inflate the custom layout for the same
//        if (currentItemView == null) {
//            currentItemView =
//                LayoutInflater.from(context).inflate(R.layout.custom_list_view, parent, false)
//        }
//
//         get the position of the view from the ArrayAdapter
//        val currentNumberPosition: NumbersView? = getItem(position)
//
//         then according to the position of the view assign the desired image for the same
//        val numbersImage = currentItemView.findViewById<ImageView>(R.id.imageView)
//        assert(currentNumberPosition != null)
//        numbersImage.setImageResource(currentNumberPosition.getNumbersImageId())
//
//         then according to the position of the view assign the desired TextView 1 for the same
//        val textView1 = currentItemView.findViewById<TextView>(R.id.textView1)
//        textView1.setText(currentNumberPosition.getNumberInDigit())
//
//         then according to the position of the view assign the desired TextView 2 for the same
//        val textView2 = currentItemView.findViewById<TextView>(R.id.textView2)
//        textView2.setText(currentNumberPosition.getNumbersInText())
//
//         then return the recyclable view
//        return currentItemView
//    }
//}
//
//
open class CustomArrayAdapterTrain private constructor(
    /**
     * Returns the context associated with this array adapter. The context is used
     * to create views from the resource passed to the constructor.
     *
     * @return The Context associated with this adapter.
     */
    val context: Context,
    /**
     * The resource indicating what views to inflate to display the content of this
     * array adapter in a drop down widget.
     */
    @param:LayoutRes private var mDropDownResource: Int,
    @IdRes textViewResourceId: Int, objects: MutableList<ExerciseTrainClass>, objsFromResources: Boolean
) :
    BaseAdapter(), ThemedSpinnerAdapter {
    /**
     * Lock used to modify the content of [.mObjects]. Any write operation
     * performed on the array should be synchronized on this lock. This lock is also
     * used by the filter (see [.getFilter] to make a synchronized copy of
     * the original array of data.
     */
//    @UnsupportedAppUsage
    private val mLock = Any()
    private val mInflater: LayoutInflater

    /**
     * The resource indicating what views to inflate to display the content of this
     * array adapter.
     */
    private val mResource: Int

    /**
     * Contains the list of objects that represent the data of this ArrayAdapter.
     * The content of this list is referred to as "the array" in the documentation.
     */
//    @UnsupportedAppUsage
    private var mObjects: MutableList<ExerciseTrainClass>

    /**
     * Indicates whether the contents of [.mObjects] came from static resources.
     */
    private var mObjectsFromResources: Boolean

    /**
     * If the inflated resource is not a TextView, `mFieldId` is used to find
     * a TextView inside the inflated views hierarchy. This field must contain the
     * identifier that matches the one defined in the resource file.
     */
    private var mFieldId = 0

    /**
     * Indicates whether or not [.notifyDataSetChanged] must be called whenever
     * [.mObjects] is modified.
     */
    private var mNotifyOnChange = true

    // A copy of the original mObjects array, initialized from and then used instead as soon as
    // the mFilter ArrayFilter is used. mObjects will then only contain the filtered values.
//    @UnsupportedAppUsage
    private var mOriginalValues: ArrayList<ExerciseTrainClass>? = null
    private var mFilter: CustomArrayAdapterTrain.ArrayFilter? = null

    /** Layout inflater used for [.getDropDownView].  */
    private var mDropDownInflater: LayoutInflater? = null

    /**
     * Constructor. This constructor will result in the underlying data collection being
     * immutable, so methods such as [.clear] will throw an exception.
     *
     * @param context The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     * instantiating views.
     * @param objects The objects to represent in the ListView.
     */
    constructor(context: Context, @LayoutRes resource: Int, objects: Array<ExerciseTrainClass>) : this(
        context,
        resource,
        0,
        Arrays.asList<ExerciseTrainClass>(*objects)
    )

    /**
     * Constructor. This constructor will result in the underlying data collection being
     * immutable, so methods such as [.clear] will throw an exception.
     *
     * @param context The current context.
     * @param resource The resource ID for a layout file containing a layout to use when
     * instantiating views.
     * @param textViewResourceId The id of the TextView within the layout resource to be populated
     * @param objects The objects to represent in the ListView.
     */
    constructor(
        context: Context, @LayoutRes resource: Int,
        @IdRes textViewResourceId: Int, objects: Array<ExerciseTrainClass>
    ) : this(context, resource, textViewResourceId, Arrays.asList<ExerciseTrainClass>(*objects))

    /**
     * Constructor
     *
     * @param context The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     * instantiating views.
     * @param objects The objects to represent in the ListView.
     */
    constructor(
        context: Context, @LayoutRes resource: Int,
        objects: MutableList<ExerciseTrainClass>
    ) : this(context, resource, 0, objects)
    /**
     * Constructor
     *
     * @param context The current context.
     * @param mDropDownResource The resource ID for a layout file containing a layout to use when
     * instantiating views.
     * @param textViewResourceId The id of the TextView within the layout resource to be populated
     * @param objects The objects to represent in the ListView.
     */
    /**
     * Constructor
     *
     * @param context The current context.
     * @param mDropDownResource The resource ID for a layout file containing a TextView to use when
     * instantiating views.
     */
    /**
     * Constructor
     *
     * @param context The current context.
     * @param resource The resource ID for a layout file containing a layout to use when
     * instantiating views.
     * @param textViewResourceId The id of the TextView within the layout resource to be populated
     */
    @JvmOverloads
    constructor(
        context: Context, @LayoutRes resource: Int,
        @IdRes textViewResourceId: Int = 0, objects: MutableList<ExerciseTrainClass> = java.util.ArrayList()
    ) : this(context, resource, textViewResourceId, objects, false)

    init {
        mInflater = LayoutInflater.from(context)
        mResource = mDropDownResource
        mObjects = objects
        mObjectsFromResources = objsFromResources
        mFieldId = textViewResourceId
    }

    /**
     * Adds the specified object at the end of the array.
     *
     * @param object The object to add at the end of the array.
     * @throws UnsupportedOperationException if the underlying data collection is immutable
     */
    fun add(`object`: ExerciseTrainClass) {
        synchronized(mLock) {
            if (mOriginalValues != null) {
                mOriginalValues!!.add(`object`)
            } else {
                mObjects!!.add(`object`)
            }
            mObjectsFromResources = false
        }
        if (mNotifyOnChange) notifyDataSetChanged()
    }

    /**
     * Adds the specified Collection at the end of the array.
     *
     * @param collection The Collection to add at the end of the array.
     * @throws UnsupportedOperationException if the <tt>addAll</tt> operation
     * is not supported by this list
     * @throws ClassCastException if the class of an element of the specified
     * collection prevents it from being added to this list
     * @throws NullPointerException if the specified collection contains one
     * or more null elements and this list does not permit null
     * elements, or if the specified collection is null
     * @throws IllegalArgumentException if some property of an element of the
     * specified collection prevents it from being added to this list
     */
    fun addAll(collection: Collection<ExerciseTrainClass>) {
        synchronized(mLock) {
            if (mOriginalValues != null) {
                mOriginalValues!!.addAll(collection)
            } else {
                mObjects!!.addAll(collection)
            }
            mObjectsFromResources = false
        }
        if (mNotifyOnChange) notifyDataSetChanged()
    }

    /**
     * Adds the specified items at the end of the array.
     *
     * @param items The items to add at the end of the array.
     * @throws UnsupportedOperationException if the underlying data collection is immutable
     */
    fun addAll(vararg items: ExerciseTrainClass) {
        synchronized(mLock) {
            if (mOriginalValues != null) {
                Collections.addAll(mOriginalValues, *items)
            } else {
                Collections.addAll(mObjects, *items)
            }
            mObjectsFromResources = false
        }
        if (mNotifyOnChange) notifyDataSetChanged()
    }

    /**
     * Inserts the specified object at the specified index in the array.
     *
     * @param object The object to insert into the array.
     * @param index The index at which the object must be inserted.
     * @throws UnsupportedOperationException if the underlying data collection is immutable
     */
    fun insert(`object`: ExerciseTrainClass, index: Int) {
        synchronized(mLock) {
            if (mOriginalValues != null) {
                mOriginalValues!!.add(index, `object`)
            } else {
                mObjects!!.add(index, `object`)
            }
            mObjectsFromResources = false
        }
        if (mNotifyOnChange) notifyDataSetChanged()
    }

    /**
     * Removes the specified object from the array.
     *
     * @param object The object to remove.
     * @throws UnsupportedOperationException if the underlying data collection is immutable
     */
    fun remove(`object`: ExerciseTrainClass) {
        synchronized(mLock) {
            if (mOriginalValues != null) {
                mOriginalValues!!.remove(`object`)
            } else {
                mObjects!!.remove(`object`)
            }
            mObjectsFromResources = false
        }
        if (mNotifyOnChange) notifyDataSetChanged()
    }

    /**
     * Remove all elements from the list.
     *
     * @throws UnsupportedOperationException if the underlying data collection is immutable
     */
    fun clear() {
        synchronized(mLock) {
            if (mOriginalValues != null) {
                mOriginalValues!!.clear()
            } else {
                mObjects!!.clear()
            }
            mObjectsFromResources = false
        }
        if (mNotifyOnChange) notifyDataSetChanged()
    }

    /**
     * Sorts the content of this adapter using the specified comparator.
     *
     * @param comparator The comparator used to sort the objects contained
     * in this adapter.
     */
    fun sort(comparator: Comparator<in ExerciseTrainClass>) {
        synchronized(mLock) {
            if (mOriginalValues != null) {
                Collections.sort(mOriginalValues, comparator)
            } else {
                Collections.sort(mObjects, comparator)
            }
        }
        if (mNotifyOnChange) notifyDataSetChanged()
    }

    override fun notifyDataSetChanged() {
        super.notifyDataSetChanged()
        mNotifyOnChange = true
    }

    /**
     * Control whether methods that change the list ([.add], [.addAll],
     * [.addAll], [.insert], [.remove], [.clear],
     * [.sort]) automatically call [.notifyDataSetChanged].  If set to
     * false, caller must manually call notifyDataSetChanged() to have the changes
     * reflected in the attached view.
     *
     * The default is true, and calling notifyDataSetChanged()
     * resets the flag to true.
     *
     * @param notifyOnChange if true, modifications to the list will
     * automatically call [                       ][.notifyDataSetChanged]
     */
    fun setNotifyOnChange(notifyOnChange: Boolean) {
        mNotifyOnChange = notifyOnChange
    }

    override fun getCount(): Int {
        return mObjects!!.size
    }

    override fun getItem(position: Int): ExerciseTrainClass {
        return mObjects!![position]
    }

    /**
     * Returns the position of the specified item in the array.
     *
     * @param item The item to retrieve the position of.
     *
     * @return The position of the specified item.
     */
    fun getPosition(item: ExerciseTrainClass): Int {
        return mObjects!!.indexOf(item)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int, convertView: View?,
        parent: ViewGroup
    ): View {
        return createViewFromResource(mInflater, position, convertView, parent, mResource)
    }

    private fun createViewFromResource(
        inflater: LayoutInflater, position: Int,
        convertView: View?, parent: ViewGroup, resource: Int
    ): View {
        val view: View
        val text: TextView?
        if (convertView == null) {
            view = inflater.inflate(resource, parent, false)
        } else {
            view = convertView
        }
        try {
            if (mFieldId == 0) {
                //  If no custom field is assigned, assume the whole resource is a TextView
                text = view as TextView
            } else {
                //  Otherwise, find the TextView field within the layout
                text = view.findViewById(mFieldId)
                if (text == null) {
                    throw java.lang.RuntimeException(
                        "Failed to find view with ID "
                                + context.resources.getResourceName(mFieldId)
                                + " in item layout"
                    )
                }
            }
        } catch (e: java.lang.ClassCastException) {
            Log.e("ArrayAdapter", "You must supply a resource ID for a TextView")
            throw java.lang.IllegalStateException(
                "ArrayAdapter requires the resource ID to be a TextView", e
            )
        }
        val item = getItem(position).name
        if (item is CharSequence) {
            text.text = item
        } else {
            text!!.text = item.toString()
        }
        return view
    }

    /**
     *
     * Sets the layout resource to create the drop down views.
     *
     * @param resource the layout resource defining the drop down views
     * @see .getDropDownView
     */
    fun setDropDownViewResource(@LayoutRes resource: Int) {
        mDropDownResource = resource
    }

    /**
     * Sets the [Resources.Theme] against which drop-down views are
     * inflated.
     *
     *
     * By default, drop-down views are inflated against the theme of the
     * [Context] passed to the adapter's constructor.
     *
     * @param theme the theme against which to inflate drop-down views or
     * `null` to use the theme from the adapter's context
     * @see .getDropDownView
     */
    override fun setDropDownViewTheme(theme: Theme?) {
        if (theme == null) {
            mDropDownInflater = null
        } else if (theme === mInflater.context.theme) {
            mDropDownInflater = mInflater
        } else {
            val context: Context = ContextThemeWrapper(context, theme)
            mDropDownInflater = LayoutInflater.from(context)
        }
    }

    override fun getDropDownViewTheme(): Theme? {
        return if (mDropDownInflater == null) null else mDropDownInflater!!.context.theme
    }

    override fun getDropDownView(
        position: Int, convertView: View?,
        parent: ViewGroup
    ): View {
        val inflater = if (mDropDownInflater == null) mInflater else mDropDownInflater!!
        return createViewFromResource(inflater, position, convertView, parent, mDropDownResource)
    }

//    override fun getFilter(): Filter {
//        if (mFilter == null) {
//            mFilter = CustomArrayAdapterTrain.ArrayFilter()
//        }
//        return mFilter
//    }

    /**
     * {@inheritDoc}
     *
     * @return values from the string array used by [.createFromResource],
     * or `null` if object was created otherwsie or if contents were dynamically changed after
     * creation.
     */
//    override fun getAutofillOptions(): Array<ExerciseTrain>? {
//         First check if app developer explicitly set them.
//        val explicitOptions: Array<ExerciseTrain> = super.getAutofillOptions()
//        if (explicitOptions != null) {
//            return explicitOptions
//        }

//         Otherwise, only return options that came from static resources.
//        if (!mObjectsFromResources || (mObjects == null) || mObjects!!.isEmpty()) {
//            return null
//        }
//        val size = mObjects!!.size
//        val options = arrayOfNulls<CharSequence>(size)
//        mObjects!!.toArray<CharSequence>(options)
//        return options
//    }

    /**
     *
     * An array filter constrains the content of the array adapter with
     * a prefix. Each item that does not start with the supplied prefix
     * is removed from the list.
     */
    private inner class ArrayFilter() : Filter() {
        override fun performFiltering(prefix: CharSequence): FilterResults {
            val results = FilterResults()
            if (mOriginalValues == null) {
                synchronized(mLock) { mOriginalValues = java.util.ArrayList(mObjects) }
            }
            if (prefix == null || prefix.length == 0) {
                val list: java.util.ArrayList<ExerciseTrainClass>
                synchronized(mLock) { list = java.util.ArrayList(mOriginalValues) }
                results.values = list
                results.count = list.size
            } else {
                val prefixString = prefix.toString().lowercase(Locale.getDefault())
                val values: java.util.ArrayList<ExerciseTrainClass>
                synchronized(mLock) { values = java.util.ArrayList(mOriginalValues) }
                val count = values.size
                val newValues = java.util.ArrayList<ExerciseTrainClass>()
                for (i in 0 until count) {
                    val value = values[i]
                    val valueText = value.name.toString().lowercase(Locale.getDefault())

                    // First match against the whole, non-splitted value
                    if (valueText.startsWith(prefixString)) {
                        newValues.add(value)
                    } else {
                        val words = valueText.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
                            .toTypedArray()
                        for (word: String in words) {
                            if (word.startsWith(prefixString)) {
                                newValues.add(value)
                                break
                            }
                        }
                    }
                }
                results.values = newValues
                results.count = newValues.size
            }
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            mObjects = results.values as MutableList<ExerciseTrainClass>
            if (results.count > 0) {
                notifyDataSetChanged()
            } else {
                notifyDataSetInvalidated()
            }
        }
    }

    companion object {
        /**
         * Creates a new ArrayAdapter from external resources. The content of the array is
         * obtained through [android.content.res.Resources.getTextArray].
         *
         * @param context The application's environment.
         * @param textArrayResId The identifier of the array to use as the data source.
         * @param textViewResId The identifier of the layout used to create views.
         *
         * @return An ArrayAdapter<CharSequence>.
        </CharSequence> */
//        fun createFromResource(
//            context: Context,
//            @ArrayRes textArrayResId: Int, @LayoutRes textViewResId: Int,
//        ): CustomArrayAdapterTrain {
//            val strings = context.resources.getTextArray(textArrayResId)
//            val test = context.resources.
//            return CustomArrayAdapterTrain(context, textViewResId, 0, Arrays.asList(*ExerciseTrain), true)
//        }
    }
}
