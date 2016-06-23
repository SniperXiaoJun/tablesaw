package com.github.lwhite1.tablesaw.filter;

import com.github.lwhite1.tablesaw.api.Table;
import com.github.lwhite1.tablesaw.columns.ColumnReference;
import com.github.lwhite1.tablesaw.columns.IntColumn;
import com.github.lwhite1.tablesaw.columns.IntColumnUtils;
import com.github.lwhite1.tablesaw.index.IntIndex;
import it.unimi.dsi.fastutil.ints.IntSet;
import org.roaringbitmap.RoaringBitmap;

/**
 */
public class IntIsIn extends ColumnFilter {

  private IntColumn filterColumn;

  public IntIsIn(ColumnReference reference, IntColumn filterColumn) {
    super(reference);
    this.filterColumn = filterColumn;
  }

  public RoaringBitmap apply(Table relation) {
    IntColumn intColumn = (IntColumn) relation.column(columnReference.getColumnName());
    IntSet firstSet = intColumn.asSet();
    firstSet.retainAll(filterColumn.data());
    return intColumn.apply(firstSet::contains);
  }
}
