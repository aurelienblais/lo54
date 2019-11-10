<div class="row">
    <div class="col-6">
        <input class="form-control" placeholder="Code" id="courses-filter-code"/>
    </div>
    <div class="col-6">
        <input class="form-control" placeholder="Name" id="courses-filter-name"/>
    </div>
</div>

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
