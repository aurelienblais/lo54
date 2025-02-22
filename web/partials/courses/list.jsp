<div class="card-header">
    <div class="row">
        <div class="col-2">
            <input class="form-control" placeholder="Code" id="courses-filter-code"/>
        </div>
        <div class="col-3">
            <input class="form-control" placeholder="Name" id="courses-filter-name"/>
        </div>
        <div class="col-3">
            <input class="form-control" placeholder="Date" id="courses-filter-date" type="date"/>
        </div>
        <div class="col-3">
            <select class="form-control location-filter" id="courses-filter-location">
                <option></option>
            </select>
        </div>
        <div class="col-1">
            <div class="btn btn-success" data-toggle="new-course">New</div>
        </div>
    </div>
</div>

<div class="card-body">
    <div class="row" id="courses-container">
        <div class="col-12 text-center my-4" style="display: none;">
            <div class="spinner-border" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <div class="col-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Code</th>
                    <th scope="col">Name</th>
                    <th></th>
                </tr>
                </thead>
                <tbody id="courses-list">

                </tbody>
            </table>
        </div>
    </div>
</div>