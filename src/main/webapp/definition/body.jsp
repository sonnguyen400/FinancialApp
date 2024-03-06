<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<body>
<div id="preloader">
    <div class="loader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3" stroke-miterlimit="10"/>
        </svg>
    </div>
</div>
<tiles:insertAttribute name="content"/>
<tiles:insertAttribute name="script"/>
</body>