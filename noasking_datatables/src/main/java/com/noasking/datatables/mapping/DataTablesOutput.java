package com.noasking.datatables.mapping;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.Collections;
import java.util.List;

public class DataTablesOutput<T> {

  /**
   * The draw counter that this object is a response to - from the draw parameter sent as part of
   * the data request. Note that it is strongly recommended for security reasons that you cast this
   * parameter to an integer, rather than simply echoing back to the client what it sent in the draw
   * parameter, in order to prevent Cross Site Scripting (XSS) attacks.
   */
  @JsonView(View.class)
  private int draw;

  /**
   * Total records, before filtering (i.e. the total number of records in the database)
   */
  @JsonView(View.class)
  private long recordsTotal = 0L;

  /**
   * Total records, after filtering (i.e. the total number of records after filtering has been
   * applied - not just the number of records being returned for this page of data).
   */
  @JsonView(View.class)
  private long recordsFiltered = 0L;

  /**
   * The data to be displayed in the table. This is an array of data source objects, one for each
   * row, which will be used by DataTables. Note that this parameter's name can be changed using the
   * ajaxDT option's dataSrc property.
   */
  @JsonView(View.class)
  private List<T> data = Collections.emptyList();

  /**
   * Optional: If an error occurs during the running of the server-side processing script, you can
   * inform the user of this error by passing back the error message to be displayed using this
   * parameter. Do not include if there is no error.
   */
  @JsonView(View.class)
  private String error;

  public DataTablesOutput(int draw, long recordsTotal, long recordsFiltered, List<T> data, String error) {
    this.draw = draw;
    this.recordsTotal = recordsTotal;
    this.recordsFiltered = recordsFiltered;
    this.data = data;
    this.error = error;
  }

  public DataTablesOutput() {
  }

  public int getDraw() {
    return draw;
  }

  public void setDraw(int draw) {
    this.draw = draw;
  }

  public long getRecordsTotal() {
    return recordsTotal;
  }

  public void setRecordsTotal(long recordsTotal) {
    this.recordsTotal = recordsTotal;
  }

  public long getRecordsFiltered() {
    return recordsFiltered;
  }

  public void setRecordsFiltered(long recordsFiltered) {
    this.recordsFiltered = recordsFiltered;
  }

  public List<T> getData() {
    return data;
  }

  public void setData(List<T> data) {
    this.data = data;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public interface View {
  }

}
