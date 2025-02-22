<div class="card-header">
    <div class="row">
        <div class="col-6">
            <input class="form-control" placeholder="Start Date" id="courses-sessions-filter-date" type="date"/>
        </div>
        <div class="col-5">
            <select class="form-control location-filter" id="courses-sessions-filter-location">
                <option></option>
            </select>
        </div>
        <div class="col-1">
            <div class="btn btn-success" data-toggle="new-course-session">New</div>
        </div>
    </div>
</div>

<div class="card-body">
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
</div>