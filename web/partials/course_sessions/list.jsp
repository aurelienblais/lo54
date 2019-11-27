<div class="row">
    <div class="col-6">
        <input class="form-control" placeholder="Start Date" id="courses-sessions-filter-date" type="date"/>
    </div>
    <div class="col-6">
        <select class="form-control location-filter" id="courses-sessions-filter-location">
            <option></option>
        </select>
    </div>
</div>


<div class="row" id="sessions-container">
    <div class="col-12 text-center my-4" style="display: none;">
        <div class="spinner-border" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>
    <div class="col-12">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Course</th>
                <th scope="col">Start date</th>
                <th scope="col">End date</th>
                <th scope="col">Capacity</th>
                <th scope="col">City</th>
            </tr>
            </thead>
            <tbody id="sessions-list">

            </tbody>
        </table>
    </div>
</div>
